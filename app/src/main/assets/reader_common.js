var system = {
    displayDensity: 1,
    compensateDensity: false,
    screenHeight: window.outerHeight,
    screenWidth: window.outerWidth,
    outputDebug: false,
    profilingON: false,
    verifyPaginationON: false,
    drawElementRectanglesON: false,
    drawReadingPositionsON: false,
    splitString: "<<>>",
    calculateOffset: false,
    usingRetargetUrl: true,
    usingAsPageCounter: false,
    usingContinuallyDiv: false
};
var pref = {
    isThemeSettled: false,
    isNightMode: false,
    themeMode: 0,
    fontFamily: undefined,
    textZoom: 1,
    lineHeight: 1.25,
    textAlign: "left",
    sdk_version: VERSION_CODES.FROYO
};
var viewport = {
    height: system.screenHeight,
    width: system.screenWidth,
    aspectRatio: -1,
    xPxPerInch: -1,
    yPxPerInch: -1
};
var ChapterState = {NOT_LOADED: 1, SCHEDULED: 2, LOADING: 3, READY: 4};
var volume = {chapters: [], chapterLoading: false, included: []};
var ResizedMediaTypes = {
    "img": true,
    "embed": true,
    "iframe": true,
    "object": true,
    "svg": true,
    "video": true,
    "audio": true
};
var LinkableElements = {"img": true, "embed": true, "object": true, "svg": true};
var ImageTagName = "img";
var AvTagName = "img";
var AvAttribName = "gwbmtype";
var AvStreamAttrib = "stream";
function jsCoordinateToJava(a) {
    if (system.compensateDensity) {
        return Math.round(a * system.displayDensity)
    } else {
        return a
    }
}
function javaCoordinateToJS(a) {
    if (system.compensateDensity) {
        return Math.round(a / system.displayDensity)
    } else {
        return a
    }
}
function PageOffsets(b, a) {
    this.from = b;
    this.to = a
}
function PageBounds(b, a) {
    this.top = b;
    this.bottom = a;
    this.touchableItems = []
}
PageBounds.prototype.containsYCoord = function (a) {
    return (this.top <= a) && (a < this.bottom)
};
PageBounds.prototype.isAboveYCoord = function (a) {
    return (this.bottom <= a)
};
function TextOffset(b, a) {
    this.text = b;
    this.offset = a
}
function Touchable(f, b, c, d, e, a) {
    this.id = f;
    this.type = b;
    this.bounds = c;
    this.source = d;
    this.hasControls = e;
    this.element = a
}
function Chapter(a) {
    this.index = a;
    this.state = ChapterState.NOT_LOADED;
    this.element = undefined;
    this.numberMediaItemsRemaining = 0;
    this.maxMediaItemsToLoad = 0;
    this.pageCount = undefined;
    this.pagesBounds = [];
    this.pagesOffsets = [];
    this.elementsToResize = [];
    this.touchableItems = [];
    this.tableItems = []
}
Chapter.prototype.reset = function () {
    this.state = ChapterState.NOT_LOADED;
    delete this.element;
    this.pagesBounds = [];
    this.pagesOffsets = [];
    this.touchableItems = [];
    this.tableItems = []
};
Chapter.prototype.highlight = function () {
    this.highlightsCount = 0;
    var f = this.element;
    try {
        for (var g = 0, n; n = volume.highlights[g]; g++) {
            var l = [];
            var p = createNodeIterator(f, NodeFilter.SHOW_TEXT);
            var b;
            while ((b = p.nextNode())) {
                var a;
                while ((a = n.exec(b.textContent))) {
                    l.push({parentNode: b, start: a.index, end: n.lastIndex})
                }
            }
            this.highlightsCount += l.length;
            window.bridge.d("Chapter[" + this.index + "].highlight() found " + l.length + " matches for target " + n);
            p.detach();
            for (var d = l.length - 1; d >= 0; d--) {
                var k = l[d];
                var h = k.parentNode;
                h.splitText(k.end);
                var o = h.splitText(k.start);
                var c = createElementWithIdAndClass(h.ownerDocument, "highlight", "SPAN");
                h.parentNode.replaceChild(c, o);
                c.appendChild(o)
            }
        }
    } catch (m) {
        window.bridge.e("Problem during highlightChapter(): " + m)
    }
};
Chapter.prototype.paginate = function () {
    try {
        var c = getZoomForTextFromPrefs();
        var d = new Paginator(this, c);
        var a = d.paginate();
        this.pagesBounds = a.pagesBounds;
        this.pageCount = this.pagesBounds.length;
        return a.timing
    } catch (b) {
        window.bridge.e("Problem during chapter.paginate(): " + b);
        return ""
    }
};
Chapter.prototype.getPageBoundsForJava = function () {
    try {
        var f = this.pagesBounds.length;
        var a = "";
        for (var c = 0; c < f; c++) {
            var d = this.pagesBounds[c];
            if (c > 0) {
                a += ","
            }
            var b = jsCoordinateToJava(d.top);
            var h = jsCoordinateToJava(d.bottom);
            if (!isInt(d.top) || !isInt(d.bottom)) {
                b = Math.floor(b);
                h = Math.floor(h)
            }
            a += b + "," + h
        }
        return a
    } catch (g) {
        window.bridge.e("Problem during chapter.getPageBoundsForJava(): " + g);
        return ""
    }
};
Chapter.prototype.getPageOffsetsForJava = function () {
    try {
        var d = this.pagesOffsets.length;
        var a = "";
        for (var b = 0; b < d; b++) {
            var c = this.pagesOffsets[b];
            if (b > 0) {
                a += ","
            }
            var h = c.from;
            var g = c.to;
            if (!isInt(h) || !isInt(g)) {
                h = Math.floor(c.from);
                g = Math.floor(c.to)
            }
            a += h + "," + g
        }
        return a
    } catch (f) {
        window.bridge.e("Problem during chapter.getPageOffsetsForJava(): " + f);
        return ""
    }
};
Chapter.prototype.getTableBoundForJava = function () {
    try {
        var a = this.tableItems.length;
        var j = "";
        for (var i = 0; i < a; i++) {
            var f = this.tableItems[i];
            if (i > 0) {
                j += system.splitString
            }
            var c = jsCoordinateToJava(f.bounds.left);
            var g = jsCoordinateToJava(f.bounds.top);
            var h = jsCoordinateToJava(f.bounds.right);
            var b = jsCoordinateToJava(f.bounds.bottom);
            if (!isInt(c) || !isInt(g) || !isInt(h) || !isInt(b)) {
                c = Math.floor(f.bounds.left);
                g = Math.floor(f.bounds.top);
                h = Math.floor(f.bounds.right);
                b = Math.floor(f.bounds.bottom)
            }
            j += c + system.splitString + g + system.splitString + h + system.splitString + b + system.splitString + f.source
        }
        return j
    } catch (d) {
        window.bridge.e("Problem during chapter.getTableBoundForJava(): " + d);
        return ""
    }
};
Chapter.prototype.applyPreferences = function () {
    try {
        if (this.element != null) {
            var b = getZoomForTextFromPrefs();
            var a = viewport.width / b;
            this.element.style.width = a + "px";
            this.element.style.zoom = b
        }
    } catch (c) {
        window.bridge.e("Uncaught exception in Chapter.prototype.applyPreferences: " + c)
    }
};
Chapter.prototype.containsYCoord = function (b) {
    var a = this.pagesBounds.length;
    if (a > 0) {
        return (this.pagesBounds[0].top <= b) && (this.pagesBounds[a - 1].bottom > b)
    }
    return false
};
Chapter.prototype.processAllMediaTypes = function (a) {
    this.processMediaElements(a, "img", "src", true, false)
};
Chapter.prototype.processElements = function (a) {
    try {
        var c = this.element.ownerDocument;
        var a = this.element;
        this.processAllMediaTypes(a);
        processTextNodes(a);
        processTableElements(a);
        return
    } catch (b) {
        window.bridge.e("Problem during processElements():" + b)
    }
};
function loadEvent_(d, a, c) {
    var b = volume.chapters[d];
    b.numberMediaItemsRemaining -= 1;
    if (system.outputDebug) {
        window.bridge.d("WK-: Chap(" + b.index + ") : " + c + "(" + a + " / " + b.maxMediaItemsToLoad + ") : remaining(" + b.numberMediaItemsRemaining + ")")
    }
    if (b.numberMediaItemsRemaining == 0 && b.state == ChapterState.READY) {
        scheduleTask(bind(chapterReady_, d), Delay.CHAPTER_READY)
    }
}
Chapter.prototype.registerReferencedDataListener = function (c, b, a) {
    if (a != "video" && a != "audio") {
        if (a == "svgImage") {
            c.setAttribute("externalResourcesRequired", true)
        }
        this.numberMediaItemsRemaining += 1;
        this.maxMediaItemsToLoad += 1;
        c.addEventListener("load", bind(loadEvent_, this.index, this.maxMediaItemsToLoad, a), false);
        c.addEventListener("error", bind(loadEvent_, this.index, this.maxMediaItemsToLoad, a + "Error"), false)
    }
};
Chapter.prototype.processMediaElements = function (e, g, r, v, A) {
    var b = getZoomForTextFromPrefs();
    var y;
    if (A) {
        y = AvTagName
    } else {
        y = g
    }
    var q = e.getElementsByTagName(y);
    for (var w = 0, f; f = q[w]; w++) {
        if (y == AvTagName) {
            var s = f.getAttribute(AvAttribName);
            if (A) {
                if (s == null || s != g) {
                    continue
                }
            } else {
                if (s != null) {
                    continue
                }
            }
        }
        if (system.usingRetargetUrl) {
            if (v) {
                if (g == "svg") {
                    var k = f.getElementsByTagName("image");
                    for (var u = 0, x; x = k[u]; u++) {
                        if (x.hasAttribute(r)) {
                            var h = x.getAttribute(r);
                            if (!isInlineDataUri(h)) {
                                this.registerReferencedDataListener(x, h, "svgImage");
                                x.setAttribute(r, retargetUrl(h))
                            }
                        }
                    }
                } else {
                    var c = new RegExp(r + "=[\"']([^\"']+)[\"']", "mi");
                    var l = c.exec(f.outerHTML);
                    if (l != null && l.length > 1) {
                        var h = l[1];
                        if (!isInlineDataUri(h)) {
                            this.registerReferencedDataListener(f, h, g);
                            f[r] = retargetUrl(h)
                        }
                    }
                }
            }
        }
        if (g == "video" || g == "audio") {
            var c = /src=["']([^"']+)["']/mi;
            if (system.usingRetargetUrl) {
                if (g == "video") {
                    var o = e.getElementsByTagName(y);
                    for (var w = 0, n; n = o[w]; w++) {
                        var z = n.getElementsByTagName("source");
                        for (var u = 0, t; t = z[u]; u++) {
                            var d = c.exec(t.outerHTML)[1];
                            if (d) {
                                t.src = retargetUrl(d)
                            }
                        }
                    }
                } else {
                    var m = e.getElementsByTagName(g);
                    for (var w = 0, p; p = m[w]; w++) {
                        var d = c.exec(p.outerHTML)[1];
                        if (d) {
                            p.src = retargetUrl(d)
                        }
                    }
                }
            }
            if (g == "audio") {
                f.controls = true;
                var a = Math.min(viewport.width * 0.8, 500);
                f.style.width = a + "px";
                f.style.height = "75px"
            } else {
                f.controls = false
            }
        }
    }
};
function addTouchableAudioVideosToList_(e, c, g) {
    var b = c.getElementsByTagName(e);
    if (!b) {
        return
    }
    for (var i = 0; i < b.length; i++) {
        var d = b[i];
        var f;
        var h;
        if (e == AvTagName) {
            f = d.getAttribute(AvAttribName);
            if (f == null) {
                continue
            }
            h = d.getAttribute(AvStreamAttrib)
        } else {
            f = e;
            h = d.getAttribute("src")
        }
        var a = getZoomedBoundingClientRect(d, 1);
        if (!a.isEmpty()) {
            g.push(new Pair(a.top, new Touchable(d.id, f, a, h, d.controls, d)))
        }
    }
}
function addTouchableImagesToList_(a, g) {
    var f = a.getElementsByTagName(ImageTagName);
    if (!f) {
        return
    }
    for (var c = 0; c < f.length; c++) {
        var b = f[c];
        if (!b.hasAttribute("src")) {
            continue
        }
        if (b.hasAttribute(AvAttribName)) {
            continue
        }
        var e = ImageTagName;
        var h = b.getAttribute("src");
        var d = getZoomedBoundingClientRect(b, 1);
        if (!d.isEmpty()) {
            g.push(new Pair(d.top, new Touchable(b.id, e, d, h, b.controls, b)))
        }
    }
}
function addTouchableLinksToList_(c, k) {
    var b = c.getElementsByTagName("a");
    if (!b) {
        return
    }
    var m = getZoomForTextFromPrefs();
    for (var l = 0; l < b.length; l++) {
        var h = b[l];
        if (!h.hasAttribute("href")) {
            continue
        }
        var e = h.href;
        var g = getZoomedClientRects(h, m);
        for (var j = 0; j < g.length; j++) {
            var a = g[j];
            if (!a.isEmpty()) {
                k.push(new Pair(a.top, new Touchable(h.id, "a", a, e, false, h)))
            }
        }
        for (var f = h.firstChild; f; f = f.nextSibling) {
            if (f.nodeType != Node.ELEMENT_NODE) {
                continue
            }
            var d = f.nodeName.toLowerCase();
            if (!LinkableElements[d]) {
                continue
            }
            var i = ResizedMediaTypes[d] ? 1 : m;
            var a = getZoomedBoundingClientRect(f, i);
            if (!a.isEmpty()) {
                k.push(new Pair(a.top, new Touchable(h.id, "a", a, e, false, h)))
            }
        }
    }
}
function addTouchableTablesToList_(f, c, h) {
    var b = c.getElementsByTagName(f);
    if (!b) {
        return
    }
    var k = getZoomForTextFromPrefs();
    for (var j = 0; j < b.length; j++) {
        var d = b[j];
        var g = f;
        var i = encodeURIComponent(d.outerHTML);
        var e = ResizedMediaTypes[name] ? 1 : k;
        var a = getZoomedBoundingClientRect(d, e);
        if (!a.isEmpty()) {
            h.push(new Pair(a.top, new Touchable(d.id, g, a, i, d.controls, d)))
        }
    }
}
function addTouchableButtonsToList_(e, c, g) {
    var b = c.getElementsByTagName(e);
    if (!b) {
        return
    }
    for (var i = 0; i < b.length; i++) {
        var d = b[i];
        var f = e;
        var h = "";
        var a = getZoomedBoundingClientRect(d, 1);
        if (!a.isEmpty()) {
            g.push(new Pair(a.top, new Touchable(d.id, f, a, h, d.controls, d)))
        }
    }
}
Chapter.prototype.cacheTouchableItemsPageInfo = function () {
    var a = this.pagesBounds.length;
    if (a <= 0) {
        window.bridge.e("cacheTouchableItemsPageInfo(): returning, pageCount is 0.");
        return
    }
    var d = this.element;
    var f = [];
    addTouchableAudioVideosToList_("video", d, f);
    addTouchableAudioVideosToList_("audio", d, f);
    addTouchableAudioVideosToList_(AvTagName, d, f);
    addTouchableLinksToList_(d, f);
    addTouchableImagesToList_(d, f);
    addTouchableButtonsToList_("button", d, f);
    var h = [];
    addTouchableTablesToList_("table", d, h);
    for (var e = 0; e < h.length; e++) {
        var b = h[e].first;
        var i = h[e].second;
        this.tableItems.push(i)
    }
    if (f.length <= 0 && system.outputDebug) {
        window.bridge.d("cacheTouchableItemsPageInfo(): returning, no touchable elements in chapter.");
        return
    }
    f.sort(comparePairsByFirstNumber);
    var g = 0;
    for (var e = 0; e < f.length; e++) {
        var b = f[e].first;
        var c = f[e].second;
        this.touchableItems.push(c)
    }
};
Chapter.prototype.resizeMediaElement = function (b, e) {
    if (!!b.style.width ^ !!b.style.height) {
        b.style.width = "";
        b.style.height = ""
    }
    var d = getDimensionInPixels(b, "height");
    var a = getDimensionInPixels(b, "width");
    var c = b.tagName.toLowerCase();
    if (d && a) {
        if (c == "svg") {
            d = Math.floor(d / e);
            a = Math.floor(a / e);
            b.style.height = d + "px";
            b.style.width = a + "px"
        } else {
            b.style.zoom = 1 / e
        }
        viewportBoundedResizeElements(b, e, d, a)
    } else {
        if (c == "svg") {
            this.elementsToResize.push(b)
        } else {
            b.style.maxHeight = (viewport.height - ViewportConstants.EXTRA_MARGIN) + "px";
            b.style.maxWidth = (viewport.width - ViewportConstants.EXTRA_MARGIN) + "px";
            b.style.zoom = 1 / e
        }
    }
};
Chapter.prototype.getTouchableItemsForJava = function () {
    if (this.pagesBounds.length > 0) {
        var c = this.touchableItems;
        var a = [];
        for (var b = 0; b < c.length; b++) {
            var d = {
                "type": c[b].type,
                "id": c[b].id,
                "bounds": {
                    "left": jsCoordinateToJava(c[b].bounds.left),
                    "top": jsCoordinateToJava(c[b].bounds.top),
                    "right": jsCoordinateToJava(c[b].bounds.right),
                    "bottom": jsCoordinateToJava(c[b].bounds.bottom),
                },
                "hasControls": c[b].hasControls ? "true" : "false",
                "source": c[b].source
            };
            a.push(d)
        }
        return JSON.stringify(a)
    } else {
        window.bridge.e("getTouchableItemsForJava: chapter only has: " + this.pagesBounds.length + " pages");
        return ""
    }
};
function getRectsStringForJava(j) {
    try {
        var c = j.length;
        var k = "";
        for (var i = 0; i < c; i++) {
            var g = j[i];
            if (i > 0) {
                k += ","
            }
            var b = jsCoordinateToJava(g.left);
            var f = jsCoordinateToJava(g.top);
            var h = jsCoordinateToJava(g.right);
            var a = jsCoordinateToJava(g.bottom);
            if (!isInt(b) || !isInt(f) || !isInt(h) || !isInt(a)) {
                b = Math.floor(g.left);
                f = Math.floor(g.top);
                h = Math.floor(g.right);
                a = Math.floor(g.bottom)
            }
            k += b + "," + f + "," + h + "," + a
        }
        return k
    } catch (d) {
        window.bridge.e("Problem during getRectsStringForJava(): " + d);
        return ""
    }
}
function chapterReady_(b) {
    var j = new Stopwatch(system.profilingON);
    var n = volume.chapters[b];
    var m = n.element.getElementsByTagName("img");
    if (m.length > 0) {
        for (var h = 0; h < m.length; ++h) {
            var q = m[h];
            if (!q.style.width && q.width && q.width * 5 < q.naturalWidth) {
                q.style.setProperty("width", q.naturalWidth + "px");
                q.style.setProperty("height", q.naturalHeight + "px");
                q.style.setProperty("max-width", viewport.width + "px");
                q.style.setProperty("max-height", viewport.height + "px")
            }
        }
    }
    var i = n.paginate();
    var s = system.calculateOffset ? calculatePageOffset(n.index, false) : 0;
    var e = n.getPageBoundsForJava();
    var k = j.getElapsedMillis();
    n.cacheTouchableItemsPageInfo();
    var a = getZoomForTextFromPrefs();
    var f = getZoomedBoundingClientRect(n.element, a);
    if (system.profilingON) {
        window.bridge.d("TIMINGS (ms) |" + "| chapter |" + n.index + "| paginate |" + k + i + "| offset |" + s)
    }
    if (system.drawElementRectanglesON) {
        var d = {top: 0, left: 0, width: viewport.width, height: 0};
        var c = n.pagesBounds.length;
        for (var o = 0; o < c; o++) {
            var g = n.pagesBounds[o];
            d.top = g.top;
            d.height = g.bottom - g.top;
            drawOutline(d, "red", 1)
        }
    }
    var t = document;
    var l = t.body;
    var r = t.getElementById("book_content");
    if (r.style.height == null || r.style.height == undefined || !endsWith(r.style.height, "px")) {
        r.style.height = f.bottom + "px"
    } else {
        var p = parseFloat(r.style.height);
        if (Math.round(p) < f.bottom) {
            r.style.height = f.bottom + "px"
        }
    }
    window.bridge.onChapterReady(n.index, n.getPageBoundsForJava(), n.getPageOffsetsForJava(), n.getTouchableItemsForJava(), n.getTableBoundForJava())
}
var CSS_OVERRIDE_SELECTORS = "div,dl,fieldset,form,h1,h2,h3,h4,h5,h6,hr,ins,noscript,ol,p,pre,script,table,code,span";
function applyPreferencesStyle_(f) {
    var e = CSS_OVERRIDE_SELECTORS + "{";
    if (pref.textAlign != null) {
        e += " text-align: " + pref.textAlign + "; "
    }
    if (pref.fontFamily != null) {
        e += '  font-family: "' + pref.fontFamily + '" !important; '
    }
    e += " line-height: " + pref.lineHeight + " !important; }";
    var c = c;
    var a = createStyleElement(f, e, c);
    var d = getHeadElement(f);
    var b = f.getElementById(c);
    if (b) {
        d.replaceChild(a, b)
    } else {
        d.appendChild(a)
    }
}
function applyPreferencesInternal_(c) {
    try {
        if (pref.isNightMode) {
            document.body.className = "night-mode"
        } else {
            document.body.className = "day-mode1";
            switch (pref.themeMode) {
                case 1:
                    document.body.className = "day-mode2";
                    break;
                case 2:
                    document.body.className = "day-mode3";
                    break;
                case 3:
                    document.body.className = "day-mode4";
                    break;
                case 4:
                    document.body.className = "day-mode5";
                    break;
                case 5:
                    document.body.className = "day-mode6";
                    break;
                case 6:
                    document.body.className = "day-mode7";
                    break;
                case 7:
                    document.body.className = "day-mode8";
                    break;
                case 8:
                    document.body.className = "day-mode9";
                    break
            }
        }
        applyPreferencesStyle_(c);
        var a = getHeadElement(c);
        var d = createStyleElement(c, "p" + "  {margin-bottom: 0em !important; }");
        a.appendChild(d);
        if (system.outputDebug) {
            window.bridge.d("Applying text preferences, zoom is: " + getZoomForTextFromPrefs() + " fontFamily is: " + pref.fontFamily + " textAlign is: " + pref.textAlign + " lineHeight is: " + pref.lineHeight)
        }
    } catch (b) {
        window.bridge.e("Uncaught exception in applyPreferencesInternal_(): " + b)
    }
}
function resetChapters_() {
    var b = volume.chapters.length;
    for (var a = 0; a < b; a++) {
        volume.chapters[a].reset()
    }
}
function initializeReader(c) {
    try {
        for (var a = 0; a < c; a++) {
            volume.chapters[a] = new Chapter(a)
        }
        resetChapters_();
        window.bridge.onReaderInitialized()
    } catch (b) {
        window.bridge.e("Uncaught exception in initializeReader(): " + b)
    }
}
function unInitializeReader() {
    try {
        window.bridge.onReaderunInitialized()
    } catch (a) {
        window.bridge.e("Uncaught exception in unInitializeReader(): " + a)
    }
}
function setDebugInfo(b, d, c, a) {
    system.profilingON = b;
    system.verifyPaginationON = d;
    system.drawElementRectanglesON = c;
    system.drawReadingPositionsON = a
}
function applyPreferences(a, b, c, n, l, p, m, d, h, o, f, i, k) {
    try {
        system.screenHeight = b;
        system.screenWidth = a;
        system.usingContinuallyDiv = k;
        viewport.height = n;
        viewport.width = c;
        viewport.aspectRatio = viewport.width / viewport.height;
        pref.sdk_version = i;
        var g = document.getElementById("android_books_inch_ruler");
        viewport.xPxPerInch = g.offsetWidth;
        viewport.yPxPerInch = g.offsetHeight;
        system.compensateDensity = false;
        system.displayDensity = f;
        pref.isNightMode = h;
        pref.themeMode = o;
        pref.fontFamily = l;
        pref.textZoom = p;
        pref.lineHeight = isString(m) ? parseFloat(m) : m;
        pref.textAlign = d;
        applyPreferencesInternal_(document);
        window.bridge.onPreferencesApplied()
    } catch (j) {
        window.bridge.e("Uncaught exception in applyPreferences(): " + j)
    }
}
function applyRuntimeSetting(c, b, f, g, l, i, a, k) {
    try {
        system.screenHeight = f;
        system.screenWidth = b;
        viewport.height = l;
        viewport.width = g;
        viewport.aspectRatio = viewport.width / viewport.height;
        pref.fontFamily = i;
        pref.lineHeight = isString(k) ? parseFloat(k) : k;
        var d = volume.chapters[c];
        if (pref.isNightMode) {
            document.body.className = "night-mode"
        } else {
            document.body.className = "day-mode1";
            switch (pref.themeMode) {
                case 1:
                    document.body.className = "day-mode2";
                    break;
                case 2:
                    document.body.className = "day-mode3";
                    break;
                case 3:
                    document.body.className = "day-mode4";
                    break;
                case 4:
                    document.body.className = "day-mode5";
                    break;
                case 5:
                    document.body.className = "day-mode6";
                    break;
                case 6:
                    document.body.className = "day-mode7";
                    break;
                case 7:
                    document.body.className = "day-mode8";
                    break;
                case 8:
                    document.body.className = "day-mode9";
                    break
            }
        }
        var j = document;
        document.body.style.lineHeight = pref.lineHeight + "em !important";
        j.body.style.lineHeight = pref.lineHeight + "em !important";
        j.body.style.fontSize = a + "px !important";
        applyPreferencesStyle_(document);
        window.scrollTo(0, 0);
        scheduleTask(function () {
            var o = getZoomForTextFromPrefs();
            d.element.style.top = 0 + "px";
            d.element.style.left = 0 + "px";
            var p = j.getElementById("book_container");
            var m = j.getElementById("book_content");
            var e = j.getElementById("chapter_content");
            var n = viewport.width / o;
            p.style.width = n + "px";
            p.style.height = "";
            p.scrollTop = "0";
            p.scrollLeft = "0";
            if (system.usingContinuallyDiv) {
                m.style.width = system.screenWidth * volume.chapters.length + "px !important";
                m.style.maxWidth = system.screenWidth * volume.chapters.length + "px !important"
            } else {
                m.style.width = n + "px"
            }
            m.style.height = "";
            m.scrollTop = "0";
            m.scrollLeft = "0";
            e.style.height = "";
            e.scrollTop = "0";
            e.scrollLeft = "0";
            d.applyPreferences();
            d.processAllMediaTypes(d.element);
            processTableElements(d.element);
            d.numberMediaItemsRemaining = 0;
            d.maxMediaItemsToLoad = 0;
            d.pagesBounds = [];
            d.pagesOffsets = [];
            d.touchableItems = [];
            d.tableItems = [];
            if (system.usingContinuallyDiv) {
                d.element.style.top = 0 + "px";
                d.element.style.left = (c * system.screenWidth) + "px";
                d.element.style.position = "absolute !important"
            }
            d.element.style.display = "none";
            d.element.offsetHeight;
            d.element.style.display = "block";
            scheduleTask(function () {
                chapterReady_(c)
            }, Delay.CHAPTER_READY)
        }, Delay.PRE_PAGINATE)
    } catch (h) {
        window.bridge.e("Uncaught exception in applyRuntimeSetting(): " + h)
    }
}
function loadChapter(d) {
    var c = 5;
    var b = 0;
    try {
        volume.chapterLoading = true;
        window.bridge.onChapterLoading(d);
        window.bridge.showSource("123",document.getElementsByTagName('html')[0].innerHTML);
        var a = volume.chapters[d];
        if (a.state == ChapterState.READY) {
            volume.chapterLoading = false;
            scheduleTask(function () {
                chapterReady_(d)
                window.bridge.showSource("4444",document.getElementsByTagName('html')[0].innerHTML);
            }, Delay.CONTENT_READY);
            return
        }
        a.numberMediaItemsRemaining = 0;
        a.maxMediaItemsToLoad = 0;
        a.state = ChapterState.LOADING;
        var g = document;
        window.scrollTo(0, 0);
        scheduleTask(function () {
            var q = new Stopwatch(system.profilingON);
            var k = null;
            k = g.getElementById("book_content");
            var e = getZoomForTextFromPrefs();
            k.style.top = 0 + "px";
            k.style.left = 0 + "px";
            window.bridge.d("system.usingContinuallyDiv="+system.usingContinuallyDiv);
            if (system.usingContinuallyDiv) {
                k.style.width = system.screenWidth * volume.chapters.length + "px !important";
                k.style.maxWidth = system.screenWidth * volume.chapters.length + "px !important"
            } else {
                k.style.width = C + "px"
            }
            var w = g.getElementById("book_container");
            w.style.height = "";
            w.scrollTop = "";
            w.scrollLeft = "0";
            var C = viewport.width / e;
            w.style.width = C + "px";
            a.element = g.getElementById("chapter_content");
            if (system.usingContinuallyDiv) {
                var u = "chapter_id_" + d + "_container";
                a.element = g.getElementById(u);
                a.element.style.top = 0 + "px";
                a.element.style.left = (d * system.screenWidth) + "px";
                a.element.style.position = "absolute !important";
                a.element.style.display = "block";
                if (!window.bridge.isForeground()) {
                    var h = volume.chapters.length;
                    for (var G = 0; G < h; G++) {
                        if (G == d) {
                            continue
                        }
                        if (volume.chapters[G].element != undefined && volume.chapters[G].element != null) {
                            volume.chapters[G].element.style.display = "none"
                        }
                    }
                }
            }
            a.applyPreferences();
            var p = q.getElapsedMillis();
            var t = window.bridge.getChapterContent(d);
            if (pref.sdk_version < VERSION_CODES.HONEYCOMB) {
                var A = g.getElementsByTagName("HEAD")[0];
                var m = new window.DOMParser();
                var J = t.indexOf("</head>");
                var s = t;
                if (J > 0) {
                    var y = t.slice(0, J);
                    s = y + "</head><body></body></html>"
                }
                var H = m.parseFromString(s, "text/xml");
                var x = H.getElementsByTagName("head")[0];
                if (x != null && x != undefined) {
                    var n = x.getElementsByTagName("style");
                    var E = x.getElementsByTagName("link");
                    var B = x.getElementsByTagName("script");
                    for (var v = 0, K; K = n[v]; ++v) {
                        if (volume.included.indexOf(K.innerHTML) > -1) {
                            continue
                        }
                        volume.included.push(K.innerHTML);
                        var z = createStyleElement(g, K.innerHTML);
                        A.appendChild(z)
                    }
                    for (var v = 0, o; o = E[v]; ++v) {
                        var j = o.href.replace(/&amp;/g, "&");
                        if (volume.included.indexOf(j) > -1) {
                            continue
                        }
                        volume.included.push(j);
                        var F = createCssLink(g, j);
                        A.appendChild(F)
                    }
                }
            }
            var I = g.createElement("div");
            I.innerHTML = t;
            var l = q.getElapsedMillis() - p;
            a.element.innerHTML = "";
            a.element.appendChild(I);
            a.processElements(I);
            var D = q.getElapsedMillis() - l;
            var r = q.getElapsedMillis();
            if (system.profilingON) {
                window.bridge.e("TIMINGS (ms) |" + "| chapter |" + d + " | load | " + r + " | style | " + p + " | append | " + D + " | before append | " + l)
            }
            window.bridge.showSource("5567777",document.getElementsByTagName('html')[0].innerHTML);
            scheduleTask(function () {
                volume.chapterLoading = false;
                if (a.numberMediaItemsRemaining == 0) {
                    chapterReady_(d)
                    window.bridge.showSource("8988999",document.getElementsByTagName('html')[0].innerHTML);
                }
                a.state = ChapterState.READY
            }, Delay.CHAPTER_READY)
        }, Delay.PRE_PAGINATE)
    } catch (f) {
        window.bridge.e("Uncaught exception in loadChapter(): " + f);
        if (b < c) {
            ++b;
            scheduleTask(function () {
                loadChapter(d)
            }, Delay.LONG)
        }
    }
}
function jump2Node(a, c) {
    try {
        var i = [];
        var h = document;
        var b = volume.chapters[a];
        var f = b.element;
        var d = f.getElementById(c);
        var j = getZoomForTextFromPrefs();
        var l = "";
        if (d != null) {
            i = getZoomedClientRects(d, j)
        } else {
            var k = false;
            if (!k) {
                window.bridge.e("could not found node by id(" + c + ")")
            }
        }
        l = getRectsStringForJava(i);
        window.bridge.onJump2Node(a, l)
    } catch (g) {
        window.bridge.e("Problem during jump2Node(): " + g);
        window.bridge.onJump2Node(a, "")
    }
}
function getNodeById(a, d) {
    if (a.length <= 0) {
        return null
    }
    for (var b = 0; b < a.length; b++) {
        if (a[b].hasChildNodes()) {
            var c = getNodeById(a[b].childNodes, d);
            if (c != null) {
                return c
            }
        }
        if (a[b].id == d) {
            return a[b]
        }
    }
    return null
}
function getNodeRect(a, i, c) {
    try {
        var k = [];
        var j = document;
        var b = volume.chapters[a];
        if (b.state != ChapterState.READY) {
            window.bridge.e("chapter " + a + "not ready")
        }
        var g = b.element;
        var d = undefined;
        var f = g.childNodes;
        d = getNodeById(f, c);
        var m = "";
        if (undefined == d) {
            window.bridge.e("can't find the node : " + c);
            window.bridge.onGetNodeRect(a, i, m);
            return
        }
        var l = getZoomForTextFromPrefs();
        if (d != null) {
            k = getZoomedClientRects(d, l)
        } else {
            window.bridge.e("could not found node by id(" + c + ")")
        }
        m = getRectsStringForJava(k);
        window.bridge.onGetNodeRect(a, i, m)
    } catch (h) {
        window.bridge.e("Problem during getNodeRect(): " + h);
        window.bridge.onGetNodeRect(a, i, "")
    }
}
function setAlignMode(f, c) {
    try {
        pref.textAlign = c;
        var h = document;
        var b = volume.chapters[f];
        var a = null;
        if (system.usingContinuallyDiv) {
            a = createNodeIterator(h.body, NodeFilter.SHOW_ELEMENT);
            h.body.style.display = "none"
        } else {
            a = createNodeIterator(b.element, NodeFilter.SHOW_ELEMENT);
            b.element.style.display = "none"
        }
        var d;
        while ((d = a.nextNode())) {
            d.style.textAlign = c + " ";
            if (c == "justify") {
                d.style.textJustify = "inter-word "
            }
        }
        a.detach();
        h.body.style.textAlign = c;
        if (system.usingContinuallyDiv) {
            h.body.style.display = "block"
        } else {
            b.element.style.display = "block"
        }
    } catch (g) {
        window.bridge.e("Problem during setAlignMode(): " + g)
    }
    window.bridge.onAlignApplied()
}
function setThemeMode(c, g, a) {
    try {
        pref.isNightMode = g;
        pref.themeMode = a;
        if (pref.isNightMode) {
            document.body.className = "night-mode"
        } else {
            document.body.className = "day-mode1";
            switch (pref.themeMode) {
                case 1:
                    document.body.className = "day-mode2";
                    break;
                case 2:
                    document.body.className = "day-mode3";
                    break;
                case 3:
                    document.body.className = "day-mode4";
                    break;
                case 4:
                    document.body.className = "day-mode5";
                    break;
                case 5:
                    document.body.className = "day-mode6";
                    break;
                case 6:
                    document.body.className = "day-mode7";
                    break;
                case 7:
                    document.body.className = "day-mode8";
                    break;
                case 8:
                    document.body.className = "day-mode9";
                    break
            }
        }
        var b = volume.chapters[c];
        var f = document;
        f.body.style.display = "none";
        f.body.style.display = "block"
    } catch (d) {
        window.bridge.e("Problem during setThemeMode(): " + d)
    }
    window.bridge.onThemeApplied()
};