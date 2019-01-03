var system={displayDensity:1,compensateDensity:false,screenHeight:window.outerHeight,screenWidth:window.outerWidth,outputDebug:false,profilingON:false,verifyPaginationON:false,drawElementRectanglesON:false,drawReadingPositionsON:false,splitString:"<<>>",calculateOffset:false,usingRetargetUrl:true,usingAsPageCounter:false};var pref={isThemeSettled:false,isNightMode:false,themeMode:0,fontFamily:undefined,textZoom:1,lineHeight:1.25,textAlign:"left",sdk_version:VERSION_CODES.FROYO};var viewport={height:system.screenHeight,width:system.screenWidth,aspectRatio:-1,xPxPerInch:-1,yPxPerInch:-1};var ChapterState={NOT_LOADED:1,SCHEDULED:2,LOADING:3,READY:4};var volume={chapters:[],iframeIndices:[],iframeSlots:1,chapterLoading:false};var ResizedMediaTypes={"img":true,"embed":true,"iframe":true,"object":true,"svg":true,"video":true,"audio":true};var LinkableElements={"img":true,"embed":true,"object":true,"svg":true};var ImageTagName="img";var AvTagName="img";var AvAttribName="gwbmtype";var AvStreamAttrib="stream";function jsCoordinateToJava(a){if(system.compensateDensity){return Math.round(a*system.displayDensity)}else{return a}}function javaCoordinateToJS(a){if(system.compensateDensity){return Math.round(a/system.displayDensity)}else{return a}}function PageOffsets(b,a){this.from=b;this.to=a}function PageBounds(b,a){this.top=b;this.bottom=a;this.touchableItems=[]}PageBounds.prototype.containsYCoord=function(a){return(this.top<=a)&&(a<this.bottom)};PageBounds.prototype.isAboveYCoord=function(a){return(this.bottom<=a)};function TextOffset(b,a){this.text=b;this.offset=a}function Touchable(f,b,c,d,e,a){this.id=f;this.type=b;this.bounds=c;this.source=d;this.hasControls=e;this.element=a}function Chapter(a){this.index=a;this.state=ChapterState.NOT_LOADED;this.element=undefined;this.numberMediaItemsRemaining=0;this.maxMediaItemsToLoad=0;this.pageCount=undefined;this.pagesBounds=[];this.pagesOffsets=[];this.elementsToResize=[];this.touchableItems=[];this.tableItems=[]}Chapter.prototype.reset=function(){this.state=ChapterState.NOT_LOADED;delete this.element;this.pagesBounds=[];this.pagesOffsets=[];this.touchableItems=[];this.tableItems=[]};Chapter.prototype.highlight=function(){this.highlightsCount=0;var f=this.element;try{for(var g=0,n;n=volume.highlights[g];g++){var l=[];var p=createNodeIterator(f,NodeFilter.SHOW_TEXT);var b;while((b=p.nextNode())){var a;while((a=n.exec(b.textContent))){l.push({parentNode:b,start:a.index,end:n.lastIndex})}}this.highlightsCount+=l.length;window.bridge.d("Chapter["+this.index+"].highlight() found "+l.length+" matches for target "+n);p.detach();for(var d=l.length-1;d>=0;d--){var k=l[d];var h=k.parentNode;h.splitText(k.end);var o=h.splitText(k.start);var c=createElementWithIdAndClass(h.ownerDocument,"highlight","SPAN");h.parentNode.replaceChild(c,o);c.appendChild(o)}}}catch(m){window.bridge.e("Problem during highlightChapter(): "+m)}};Chapter.prototype.paginate=function(){try{var c=getZoomForTextFromPrefs();var d=new Paginator(this,c);var a=d.paginate();this.pagesBounds=a.pagesBounds;this.pageCount=this.pagesBounds.length;return a.timing}catch(b){window.bridge.e("Problem during chapter.paginate(): "+b);return""}};Chapter.prototype.getPageBoundsForJava=function(){try{var f=this.pagesBounds.length;var a="";for(var c=0;c<f;c++){var d=this.pagesBounds[c];if(c>0){a+=","}var b=jsCoordinateToJava(d.top);var h=jsCoordinateToJava(d.bottom);if(!isInt(d.top)||!isInt(d.bottom)){b=Math.floor(b);h=Math.floor(h)}a+=b+","+h}return a}catch(g){window.bridge.e("Problem during chapter.getPageBoundsForJava(): "+g);return""}};Chapter.prototype.getPageOffsetsForJava=function(){try{var d=this.pagesOffsets.length;var a="";for(var b=0;b<d;b++){var c=this.pagesOffsets[b];if(b>0){a+=","}var h=c.from;var g=c.to;if(!isInt(h)||!isInt(g)){h=Math.floor(c.from);g=Math.floor(c.to)}a+=h+","+g}return a}catch(f){window.bridge.e("Problem during chapter.getPageOffsetsForJava(): "+f);return""}};Chapter.prototype.getTableBoundForJava=function(){try{var a=this.tableItems.length;var j="";for(var i=0;i<a;i++){var f=this.tableItems[i];if(i>0){j+=system.splitString}var c=jsCoordinateToJava(f.bounds.left);var g=jsCoordinateToJava(f.bounds.top);var h=jsCoordinateToJava(f.bounds.right);var b=jsCoordinateToJava(f.bounds.bottom);if(!isInt(c)||!isInt(g)||!isInt(h)||!isInt(b)){c=Math.floor(f.bounds.left);g=Math.floor(f.bounds.top);h=Math.floor(f.bounds.right);b=Math.floor(f.bounds.bottom)}j+=c+system.splitString+g+system.splitString+h+system.splitString+b+system.splitString+f.source}return j}catch(d){window.bridge.e("Problem during chapter.getTableBoundForJava(): "+d);return""}};Chapter.prototype.applyPreferences=function(){try{if(this.element!=null){var b=getZoomForTextFromPrefs();var a=viewport.width/b;this.element.style.width=a+"px";this.element.style.zoom=b}}catch(c){window.bridge.e("Uncaught exception in Chapter.prototype.applyPreferences: "+c)}};Chapter.prototype.containsYCoord=function(b){var a=this.pagesBounds.length;if(a>0){return(this.pagesBounds[0].top<=b)&&(this.pagesBounds[a-1].bottom>b)}return false};Chapter.prototype.processAllMediaTypes=function(a){this.processMediaElements(a,"img","src",true,false)};Chapter.prototype.processElements=function(a){try{var c=this.element.ownerDocument;this.processAllMediaTypes(a);processTextNodes(a);processTableElements(a);return}catch(b){window.bridge.e("Problem during processElements():"+b)}};function loadEvent_(d,a,c){var b=volume.chapters[d];b.numberMediaItemsRemaining-=1;if(system.outputDebug){window.bridge.d("WK-: Chap("+b.index+") : "+c+"("+a+" / "+b.maxMediaItemsToLoad+") : remaining("+b.numberMediaItemsRemaining+")")}if(b.numberMediaItemsRemaining==0&&b.state==ChapterState.READY){scheduleTask(bind(chapterReady_,d),Delay.CHAPTER_READY)}}Chapter.prototype.registerReferencedDataListener=function(c,b,a){if(a!="video"&&a!="audio"){if(a=="svgImage"){c.setAttribute("externalResourcesRequired",true)}this.numberMediaItemsRemaining+=1;this.maxMediaItemsToLoad+=1;c.addEventListener("load",bind(loadEvent_,this.index,this.maxMediaItemsToLoad,a),false);c.addEventListener("error",bind(loadEvent_,this.index,this.maxMediaItemsToLoad,a+"Error"),false)}};Chapter.prototype.processMediaElements=function(e,g,r,v,A){var b=getZoomForTextFromPrefs();var y;if(A){y=AvTagName}else{y=g}var q=e.getElementsByTagName(y);for(var w=0,f;f=q[w];w++){if(y==AvTagName){var s=f.getAttribute(AvAttribName);if(A){if(s==null||s!=g){continue}}else{if(s!=null){continue}}}if(system.usingRetargetUrl){if(v){if(g=="svg"){var k=f.getElementsByTagName("image");for(var u=0,x;x=k[u];u++){if(x.hasAttribute(r)){var h=x.getAttribute(r);if(!isInlineDataUri(h)){this.registerReferencedDataListener(x,h,"svgImage");x.setAttribute(r,retargetUrl(h))}}}}else{var c=new RegExp(r+"=[\"']([^\"']+)[\"']","mi");var l=c.exec(f.outerHTML);if(l!=null&&l.length>1){var h=l[1];if(!isInlineDataUri(h)){this.registerReferencedDataListener(f,h,g);f[r]=retargetUrl(h)}}}}}if(g=="video"||g=="audio"){var c=/src=["']([^"']+)["']/mi;if(system.usingRetargetUrl){if(g=="video"){var o=e.getElementsByTagName(y);for(var w=0,n;n=o[w];w++){var z=n.getElementsByTagName("source");for(var u=0,t;t=z[u];u++){var d=c.exec(t.outerHTML)[1];if(d){t.src=retargetUrl(d)}}}}else{var m=e.getElementsByTagName(g);for(var w=0,p;p=m[w];w++){var d=c.exec(p.outerHTML)[1];if(d){p.src=retargetUrl(d)}}}}if(g=="audio"){f.controls=true;var a=Math.min(viewport.width*0.8,500);f.style.width=a+"px";f.style.height="75px"}else{f.controls=false}}}};function addTouchableAudioVideosToList_(e,c,g){var b=c.getElementsByTagName(e);if(!b){return}for(var i=0;i<b.length;i++){var d=b[i];var f;var h;if(e==AvTagName){f=d.getAttribute(AvAttribName);if(f==null){continue}h=d.getAttribute(AvStreamAttrib)}else{f=e;h=d.getAttribute("src")}var a=getZoomedBoundingClientRect(d,1);if(!a.isEmpty()){g.push(new Pair(a.top,new Touchable(d.id,f,a,h,d.controls,d)))}}}function addTouchableImagesToList_(a,g){var f=a.getElementsByTagName(ImageTagName);if(!f){return}for(var c=0;c<f.length;c++){var b=f[c];if(!b.hasAttribute("src")){continue}if(b.hasAttribute(AvAttribName)){continue}var e=ImageTagName;var h=b.getAttribute("src");var d=getZoomedBoundingClientRect(b,1);if(!d.isEmpty()){g.push(new Pair(d.top,new Touchable(b.id,e,d,h,b.controls,b)))}}}function addTouchableLinksToList_(c,k){var b=c.getElementsByTagName("a");if(!b){return}var m=getZoomForTextFromPrefs();for(var l=0;l<b.length;l++){var h=b[l];if(!h.hasAttribute("href")){continue}var e=h.href;var g=getZoomedClientRects(h,m);for(var j=0;j<g.length;j++){var a=g[j];if(!a.isEmpty()){k.push(new Pair(a.top,new Touchable(h.id,"a",a,e,false,h)))}}for(var f=h.firstChild;f;f=f.nextSibling){if(f.nodeType!=Node.ELEMENT_NODE){continue}var d=f.nodeName.toLowerCase();if(!LinkableElements[d]){continue}var i=ResizedMediaTypes[d]?1:m;var a=getZoomedBoundingClientRect(f,i);if(!a.isEmpty()){k.push(new Pair(a.top,new Touchable(h.id,"a",a,e,false,h)))}}}}function addTouchableTablesToList_(f,c,h){var b=c.getElementsByTagName(f);if(!b){return}var k=getZoomForTextFromPrefs();for(var j=0;j<b.length;j++){var d=b[j];var g=f;var i=encodeURIComponent(d.outerHTML);var e=ResizedMediaTypes[name]?1:k;var a=getZoomedBoundingClientRect(d,e);if(!a.isEmpty()){h.push(new Pair(a.top,new Touchable(d.id,g,a,i,d.controls,d)))}}}function addTouchableButtonsToList_(e,c,g){var b=c.getElementsByTagName(e);if(!b){return}for(var i=0;i<b.length;i++){var d=b[i];var f=e;var h="";var a=getZoomedBoundingClientRect(d,1);if(!a.isEmpty()){g.push(new Pair(a.top,new Touchable(d.id,f,a,h,d.controls,d)))}}}Chapter.prototype.cacheTouchableItemsPageInfo=function(){var a=this.pagesBounds.length;if(a<=0){window.bridge.e("cacheTouchableItemsPageInfo(): returning, pageCount is 0.");return}var d=this.element;var f=[];addTouchableAudioVideosToList_("video",d,f);addTouchableAudioVideosToList_("audio",d,f);addTouchableAudioVideosToList_(AvTagName,d,f);addTouchableLinksToList_(d,f);addTouchableImagesToList_(d,f);addTouchableButtonsToList_("button",d,f);var h=[];addTouchableTablesToList_("table",d,h);for(var e=0;e<h.length;e++){var b=h[e].first;var i=h[e].second;this.tableItems.push(i)}if(f.length<=0&&system.outputDebug){window.bridge.d("cacheTouchableItemsPageInfo(): returning, no touchable elements in chapter.");return}f.sort(comparePairsByFirstNumber);var g=0;for(var e=0;e<f.length;e++){var b=f[e].first;var c=f[e].second;this.touchableItems.push(c)}};Chapter.prototype.resizeMediaElement=function(b,e){if(!!b.style.width^!!b.style.height){b.style.width="";b.style.height=""}var d=getDimensionInPixels(b,"height");var a=getDimensionInPixels(b,"width");var c=b.tagName.toLowerCase();if(d&&a){if(c=="svg"){d=Math.floor(d/e);a=Math.floor(a/e);b.style.height=d+"px";b.style.width=a+"px"}else{b.style.zoom=1/e}viewportBoundedResizeElements(b,e,d,a)}else{if(c=="svg"){this.elementsToResize.push(b)}else{b.style.maxHeight=(viewport.height-ViewportConstants.EXTRA_MARGIN)+"px";b.style.maxWidth=(viewport.width-ViewportConstants.EXTRA_MARGIN)+"px";b.style.zoom=1/e}}};Chapter.prototype.getTouchableItemsForJava=function(){if(this.pagesBounds.length>0){var c=this.touchableItems;var a=[];for(var b=0;b<c.length;b++){var d={"type":c[b].type,"id":c[b].id,"bounds":{"left":jsCoordinateToJava(c[b].bounds.left),"top":jsCoordinateToJava(c[b].bounds.top),"right":jsCoordinateToJava(c[b].bounds.right),"bottom":jsCoordinateToJava(c[b].bounds.bottom),},"hasControls":c[b].hasControls?"true":"false","source":c[b].source};a.push(d)}return JSON.stringify(a)}else{window.bridge.e("getTouchableItemsForJava: chapter only has: "+this.pagesBounds.length+" pages");return""}};function getRectsStringForJava(j){try{var c=j.length;var k="";for(var i=0;i<c;i++){var g=j[i];if(i>0){k+=","}var b=jsCoordinateToJava(g.left);var f=jsCoordinateToJava(g.top);var h=jsCoordinateToJava(g.right);var a=jsCoordinateToJava(g.bottom);if(!isInt(b)||!isInt(f)||!isInt(h)||!isInt(a)){b=Math.floor(g.left);f=Math.floor(g.top);h=Math.floor(g.right);a=Math.floor(g.bottom)}k+=b+","+f+","+h+","+a}return k}catch(d){window.bridge.e("Problem during getRectsStringForJava(): "+d);return""}}function chapterReady_(c){var j=new Stopwatch(system.profilingON);var n=volume.chapters[c];var r=getFrame_(getSlot_(c));var q=r.contentDocument||r.contentWindow.document;var u=r.contentWindow||r.contentDocument.defaultView;var v=q;var m=q.body;var l=v.getElementsByTagName("img");if(l.length>0){for(var h=0;h<l.length;++h){var p=l[h];if(!p.style.width&&p.width&&p.width*5<p.naturalWidth){p.style.setProperty("width",p.naturalWidth+"px");p.style.setProperty("height",p.naturalHeight+"px");p.style.setProperty("max-width",viewport.width+"px");p.style.setProperty("max-height",viewport.height+"px")}}}var i=n.paginate();var t=system.calculateOffset?calculatePageOffset(n.index,false):0;var e=n.getPageBoundsForJava();var k=j.getElapsedMillis();n.cacheTouchableItemsPageInfo();var a=getZoomForTextFromPrefs();var f=getZoomedBoundingClientRect(n.element,a);if(system.profilingON){window.bridge.d("TIMINGS (ms) |"+"| chapter |"+n.index+"| paginate |"+k+i+"| offset |"+t)}if(system.drawElementRectanglesON){var d={top:0,left:0,width:viewport.width,height:0};var b=n.pagesBounds.length;for(var o=0;o<b;o++){var g=n.pagesBounds[o];d.top=g.top;d.height=g.bottom-g.top;drawOutline(d,"red",1)}}var s=v.getElementById("book_content");r.style.height=f.bottom+"px";m.style.height=f.bottom+"px";s.style.height=f.bottom+"px";window.bridge.onChapterReady(n.index,n.getPageBoundsForJava(),n.getPageOffsetsForJava(),n.getTouchableItemsForJava(),n.getTableBoundForJava())}var CSS_OVERRIDE_SELECTORS="div,dl,fieldset,form,h1,h2,h3,h4,h5,h6,hr,ins,noscript,ol,p,pre,script,table,code,span";function applyPreferencesStyle_(f){var e=CSS_OVERRIDE_SELECTORS+"{";if(pref.textAlign!=null){e+=" text-align: "+pref.textAlign+"; "}if(pref.fontFamily!=null){e+='  font-family: "'+pref.fontFamily+'" !important; '}e+=" line-height: "+pref.lineHeight+" !important; }";var c=c;var a=createStyleElement(f,e,c);var d=getHeadElement(f);var b=f.getElementById(c);if(b){d.replaceChild(a,b)}else{d.appendChild(a)}}function applyPreferencesInternal_(c){try{c.body.className=pref.isNightMode?"night-mode":"day-mode";applyPreferencesStyle_(c);var a=getHeadElement(c);var d=createStyleElement(c,"p"+"  {margin-bottom: 0em !important; }");a.appendChild(d);if(system.outputDebug){window.bridge.d("Applying text preferences, zoom is: "+getZoomForTextFromPrefs()+" fontFamily is: "+pref.fontFamily+" textAlign is: "+pref.textAlign+" lineHeight is: "+pref.lineHeight)}}catch(b){window.bridge.e("Uncaught exception in applyPreferencesInternal_(): "+b)}}function convertSlotToIframeX(a){return 0;return(a+1)<<12}function resetSlots_(){for(var a=0;a<volume.iframeSlots;a++){volume.iframeIndices[a]=-1}}function getSlot_(a){return 0;return(a+1)%volume.iframeSlots}function getFrame_(a){return document.getElementById("iFrame"+a)||null}function resetOneFrame_(h){var c=viewport.width;var i=viewport.height;var b=$get("iframe_container");var e=getFrame_(h);var g=null;if(e!=null){g=e}else{g=document.createElement("iframe");g.id="iFrame"+h;b.appendChild(g)}g.style.width=c+"px";g.style.height=i+"px";g.style.border=0;g.style.overflowX="hidden";g.style.overflowY="hidden";g.style.frameborder="0";g.style.top="0px";g.style.left=convertSlotToIframeX(h)+"px";var f=g.contentDocument||g.contentWindow.document;var d=g.contentWindow||g.contentDocument.defaultView;var a=f.body;a.className=pref.isNightMode?"night-mode":"day-mode";a.style.width=c+"px";a.style.height=i+"px";a.style.overflowX="hidden";g.style.overflowY="hidden";a.style.top="0px";a.style.border="none";a.style.left="0px";return g}function resetAllFrames_(){for(var a=0;a<volume.iframeSlots;a++){resetOneFrame_(a);volume.iframeIndices[a]=-1}}function updateOneFrame_(b){var a=getSlot_(b);resetOneFrame_(a);return}function resetChapters_(){try{resetSlots_()}catch(c){window.bridge.e("Uncaught exception in resetChapters_(): "+c)}var b=volume.chapters.length;for(var a=0;a<b;a++){volume.chapters[a].reset()}}function initializeReader(c){try{volume.iframeIndices=[];for(var a=0;a<c;a++){volume.chapters[a]=new Chapter(a)}resetChapters_();window.bridge.onReaderInitialized()}catch(b){window.bridge.e("Uncaught exception in initializeReader(): "+b)}}function unInitializeReader(){try{window.bridge.onReaderunInitialized()}catch(a){window.bridge.e("Uncaught exception in unInitializeReader(): "+a)}}function setDebugInfo(b,d,c,a){system.profilingON=b;system.verifyPaginationON=d;system.drawElementRectanglesON=c;system.drawReadingPositionsON=a}function applyPreferences(a,b,c,m,k,o,l,d,h,n,f,i){try{system.screenHeight=b;system.screenWidth=a;viewport.height=m;viewport.width=c;viewport.aspectRatio=viewport.width/viewport.height;pref.sdk_version=i;var g=document.getElementById("android_books_inch_ruler");viewport.xPxPerInch=g.offsetWidth;viewport.yPxPerInch=g.offsetHeight;system.compensateDensity=false;system.displayDensity=f;pref.isNightMode=h;pref.themeMode=n;pref.fontFamily=k;pref.textZoom=o;pref.lineHeight=isString(l)?parseFloat(l):l;pref.textAlign=d;window.bridge.onPreferencesApplied()}catch(j){window.bridge.e("Uncaught exception in applyPreferences(): "+j)}}function applyRuntimeSetting(a,o,t,n,j,p,i,c){try{system.screenHeight=t;system.screenWidth=o;viewport.height=j;viewport.width=n;viewport.aspectRatio=viewport.width/viewport.height;pref.fontFamily=p;pref.lineHeight=isString(c)?parseFloat(c):c;window.scrollTo(0,0);var u=getSlot_(a);var m=getFrame_(u);m.contentWindow.scrollTo(0,0);var l=m.contentDocument||m.contentWindow.document;var s=m.contentWindow||m.contentDocument.defaultView;var v=l;var g=volume.chapters[a];s.scrollTo(0,0);v.body.className=pref.isNightMode?"night-mode":"day-mode";document.body.style.lineHeight=pref.lineHeight+"em !important";v.body.style.lineHeight=pref.lineHeight+"em !important";var k=getHeadElement(v);var d=prefStyleId;var r;for(var h=0;r=v.styleSheets[h];h++){if(r.ownerNode.id==d){break}}if(isDef(r)){}applyPreferencesStyle_(v);v.body.style.fontSize=i+"px !important";scheduleTask(function(){var y=getZoomForTextFromPrefs();g.element.style.top=0+"px";g.element.style.left=0+"px";m.style.width=viewport.width+"px";m.style.height=viewport.height+"px";v.body.style.width=viewport.width+"px";v.body.style.height=viewport.height+"px";var z=v.getElementById("book_container");var w=v.getElementById("book_content");var e=v.getElementById("chapter_content");var x=viewport.width/y;z.style.width=x+"px";z.style.height="";z.scrollTop="0";w.style.width=x+"px";w.style.height="";w.scrollTop="0";e.style.height="";e.scrollTop="0";g.applyPreferences();g.processAllMediaTypes(g.element);processTableElements(g.element);g.numberMediaItemsRemaining=0;g.maxMediaItemsToLoad=0;g.pagesBounds=[];g.pagesOffsets=[];g.touchableItems=[];g.tableItems=[];g.element.style.display="none";g.element.offsetHeight;g.element.style.display="block";scheduleTask(function(){chapterReady_(a)},Delay.CHAPTER_READY)},Delay.PRE_PAGINATE);for(var f=0;f<volume.iframeSlots;f++){if(u==f){continue}var b=volume.iframeIndices[f];if(b>=0){var m=getFrame_(f);m.contentWindow.scrollTo(0,0);var l=m.contentDocument||m.contentWindow.document;var s=m.contentWindow||m.contentDocument.defaultView;var v=l;var g=volume.chapters[b];s.scrollTo(0,0);v.body.className=pref.isNightMode?"night-mode":"day-mode";document.body.style.lineHeight=pref.lineHeight+"em !important";v.body.style.lineHeight=pref.lineHeight+"em !important";v.body.style.fontSize=i+"px  !important";applyPreferencesStyle_(v);scheduleTask(function(){var y=getZoomForTextFromPrefs();g.element.style.top=0+"px";g.element.style.left=0+"px";m.style.width=viewport.width+"px";m.style.height=viewport.height+"px";v.body.style.width=viewport.width+"px";v.body.style.height=viewport.height+"px";var z=v.getElementById("book_container");var w=v.getElementById("book_content");var e=v.getElementById("chapter_content");var x=viewport.width/y;z.style.width=x+"px";z.style.height="";z.scrollTop="0";w.style.width=x+"px";w.style.height="";w.scrollTop="0";e.style.height="";e.scrollTop="0";g.applyPreferences();g.processAllMediaTypes(g.element);processTableElements(g.element);g.numberMediaItemsRemaining=0;g.maxMediaItemsToLoad=0;g.pagesBounds=[];g.pagesOffsets=[];g.touchableItems=[];g.tableItems=[];g.element.style.display="none";g.element.offsetHeight;g.element.style.display="block";scheduleTask(function(){chapterReady_(b)},Delay.CHAPTER_READY)},Delay.PRE_PAGINATE)}}}catch(q){window.bridge.e("Uncaught exception in applyRuntimeSetting(): "+q)}}function loadChapter(a){var f=5;var j=0;try{volume.chapterLoading=true;window.bridge.onChapterLoading(a);var b=volume.chapters[a];b.numberMediaItemsRemaining=0;b.maxMediaItemsToLoad=0;b.state=ChapterState.LOADING;updateOneFrame_(a);var d=getFrame_(getSlot_(a));var g=d.contentDocument||d.contentWindow.document;var c=d.contentWindow||d.contentDocument.defaultView;var k=g;k.body.innerHTML="";var i=window.bridge.getChapterContent(a);var l=function(){var n=new Stopwatch(system.profilingON);if(!pref.isThemeSettled){pref.isThemeSettled=true;if(pref.isNightMode){document.body.className="night-mode"}else{document.body.className="day-mode1";switch(pref.themeMode){case 1:document.body.className="day-mode2";break;case 2:document.body.className="day-mode3";break;case 3:document.body.className="day-mode4";break;case 4:document.body.className="day-mode5";break;case 5:document.body.className="day-mode6";break;case 6:document.body.className="day-mode7";break;case 7:document.body.className="day-mode8";break;case 8:document.body.className="day-mode9";break}}}applyPreferencesInternal_(k);var o=null;o=k.getElementById("book_content");b.element=o;var q=getZoomForTextFromPrefs();o.style.top=0+"px";o.style.left=0+"px";var m=k.getElementById("book_container");var p=viewport.width/q;m.style.width=p+"px";o.style.width=p+"px";b.applyPreferences();b.processElements(b.element);var e=n.getElapsedMillis();if(system.profilingON){window.bridge.d("TIMINGS (ms) |"+"| chapter |"+a+"| load |"+e)}scheduleTask(function(){volume.chapterLoading=false;if(b.numberMediaItemsRemaining==0){chapterReady_(a)}b.state=ChapterState.READY},Delay.CHAPTER_READY)};if(0&&k.addEventListener){k.open("text/html","replace");k.write(i);k.addEventListener("DOMContentLoaded",l,false);k.close()}else{k.open("text/html","replace");k.write(i);c.addEventListener("load",l,false);k.close()}}catch(h){window.bridge.e("Uncaught exception in loadChapter(): "+h);if(j<f){++j;scheduleTask(function(){loadChapter(a)},Delay.LONG)}}}function jump2Node(a,f){try{var m=[];var j=getSlot_(a);var d=getFrame_(j);var i=d.contentDocument||d.contentWindow.document;var c=d.contentWindow||d.contentDocument.defaultView;var l=i;var b=volume.chapters[a];var h=b.element;var g=l.getElementById(f);var n=getZoomForTextFromPrefs();var p="";if(g!=null){m=getZoomedClientRects(g,n)}else{var o=false;if(!o){window.bridge.e("could not found node by id("+f+")")}}p=getRectsStringForJava(m);window.bridge.onJump2Node(a,p)}catch(k){window.bridge.e("Problem during jump2Node(): "+k);window.bridge.onJump2Node(a,"")}}function setAlignMode(a,b){try{pref.textAlign=b;var k=getSlot_(a);var f=getFrame_(k);var h=f.contentDocument||f.contentWindow.document;var d=f.contentWindow||f.contentDocument.defaultView;var n=h;var c=volume.chapters[a];var m=createNodeIterator(c.element,NodeFilter.SHOW_ELEMENT);c.element.style.display="none";var g;while((g=m.nextNode())){g.style.textAlign=b+" ";if(b=="justify"){g.style.textJustify="inter-word "}}m.detach();n.body.style.textAlign=b;c.element.style.display="block";for(var j=0;j<volume.iframeSlots;j++){if(k==j){continue}var i=volume.iframeIndices[j];if(i>=0){var f=getFrame_(i);var h=f.contentDocument||f.contentWindow.document;var d=f.contentWindow||f.contentDocument.defaultView;var n=h;var c=volume.chapters[i];var m=createNodeIterator(c.element,NodeFilter.SHOW_ELEMENT);c.element.style.display="none";var g;while((g=m.nextNode())){g.style.textAlign=b+" ";if(b=="justify"){g.style.textJustify="inter-word "}}m.detach();n.body.style.textAlign=b;c.element.style.display="block"}}}catch(l){window.bridge.e("Problem during setAlignMode(): "+l)}window.bridge.onAlignApplied()}function setThemeMode(a,f,m){try{pref.isNightMode=f;pref.themeMode=m;if(pref.isNightMode){document.body.className="night-mode"}else{document.body.className="day-mode1";switch(pref.themeMode){case 1:document.body.className="day-mode2";break;case 2:document.body.className="day-mode3";break;case 3:document.body.className="day-mode4";break;case 4:document.body.className="day-mode5";break;case 5:document.body.className="day-mode6";break;case 6:document.body.className="day-mode7";break;case 7:document.body.className="day-mode8";break;case 8:document.body.className="day-mode9";break}}var b=volume.chapters[a];var j=getSlot_(a);var d=getFrame_(j);var g=d.contentDocument||d.contentWindow.document;var c=d.contentWindow||d.contentDocument.defaultView;var l=g;l.body.className=pref.isNightMode?"night-mode":"day-mode";l.body.style.display="none";l.body.style.display="block";for(var i=0;i<volume.iframeSlots;i++){if(j==i){continue}var h=volume.iframeIndices[i];if(h>=0){var b=volume.chapters[h];var d=getFrame_(i);var g=d.contentDocument||d.contentWindow.document;var c=d.contentWindow||d.contentDocument.defaultView;var l=g;l.body.className=pref.isNightMode?"night-mode":"day-mode";b.element.style.display="none";b.element.style.display="block"}}}catch(k){window.bridge.e("Problem during setThemeMode(): "+k)}window.bridge.onThemeApplied()};