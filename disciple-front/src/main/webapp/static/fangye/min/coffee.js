(function(T,S){var I="",C,B,N,G={Webkit:"webkit",Moz:"",O:"o"},M=window.document,K=M.createElement("div"),L=/^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,F,E,V,H,P,O,Q,R,J,A={};function D(W){return W.replace(/([a-z])([A-Z])/,"$1-$2").toLowerCase()}function U(W){return C?C+W:W.toLowerCase()}T.each(G,function(X,W){if(K.style[X+"TransitionProperty"]!==S){I="-"+X.toLowerCase()+"-";C=W;return false}});F=I+"transform";A[E=I+"transition-property"]=A[V=I+"transition-duration"]=A[P=I+"transition-delay"]=A[H=I+"transition-timing-function"]=A[O=I+"animation-name"]=A[Q=I+"animation-duration"]=A[J=I+"animation-delay"]=A[R=I+"animation-timing-function"]="";T.fx={off:(C===S&&K.style.transitionProperty===S),speeds:{_default:400,fast:200,slow:600},cssPrefix:I,transitionEnd:U("TransitionEnd"),animationEnd:U("AnimationEnd")};T.fn.animate=function(Y,Z,X,W,a){if(T.isFunction(Z)){W=Z,X=S,Z=S}if(T.isFunction(X)){W=X,X=S}if(T.isPlainObject(Z)){X=Z.easing,W=Z.complete,a=Z.delay,Z=Z.duration}if(Z){Z=(typeof Z=="number"?Z:(T.fx.speeds[Z]||T.fx.speeds._default))/1000}if(a){a=parseFloat(a)/1000}return this.anim(Y,Z,X,W,a)};T.fn.anim=function(d,a,j,W,Y){var e,f={},h,Z="",b=this,g,X=T.fx.transitionEnd,c=false;if(a===S){a=T.fx.speeds._default/1000}if(Y===S){Y=0}if(T.fx.off){a=0}if(typeof d=="string"){f[O]=d;f[Q]=a+"s";f[J]=Y+"s";f[R]=(j||"linear");X=T.fx.animationEnd}else{h=[];for(e in d){if(L.test(e)){Z+=e+"("+d[e]+") "}else{f[e]=d[e],h.push(D(e))}}if(Z){f[F]=Z,h.push(F)}if(a>0&&typeof d==="object"){f[E]=h.join(", ");f[V]=a+"s";f[P]=Y+"s";f[H]=(j||"linear")}}g=function(k){if(typeof k!=="undefined"){if(k.target!==k.currentTarget){return}T(k.target).unbind(X,g)}else{T(this).unbind(X,g)}c=true;T(this).css(A);W&&W.call(this)};if(a>0){this.bind(X,g);setTimeout(function(){if(c){return}g.call(b)},(a*1000)+25)}this.size()&&this.get(0).clientLeft;this.css(f);if(a<=0){setTimeout(function(){b.each(function(){g.call(this)})},0)}return this};K=null})(Zepto);(function(A){A.fn.coffee=function(N){var F=null;var M=null;var K="cubic-bezier(.09,.64,.16,.94)";var H=A(this);var J=A.extend({},A.fn.coffee.defaults,N);var C=J.steamWidth;var L=A('<div class="coffee-steam-box"></div>').css({"height":J.steamHeight,"width":J.steamWidth,"left":60,"top":-50,"position":"absolute","overflow":"hidden","z-index":0}).appendTo(H);A.fn.coffee.stop=function(){clearInterval(F);clearInterval(M)};A.fn.coffee.start=function(){F=setInterval(function(){D()},B(J.steamInterval/2,J.steamInterval*2));M=setInterval(function(){E()},B(100,1000)+B(1000,3000))};return H;function D(){var V=B(8,J.steamMaxSize);var O=I(1,J.steamsFontFamily);var Q="#"+I(6,"0123456789ABCDEF");var T=B(0,44);var S=B(-90,89);var P=G(0.4,1);var U=A.fx.cssPrefix+"transform";U=U+":rotate("+S+"deg) scale("+P+");";var W=A('<span class="coffee-steam">'+I(1,J.steams)+"</span>");var R=B(0,C-J.steamWidth-V);if(R>T){R=B(0,T)}W.css({"position":"absolute","left":T,"top":J.steamHeight,"font-size:":V+"px","color":Q,"font-family":O,"display":"block","opacity":1}).attr("style",W.attr("style")+U).appendTo(L).animate({top:B(J.steamHeight/2,0),left:R,opacity:0},B(J.steamFlyTime/2,J.steamFlyTime*1.2),K,function(){W.remove();W=null})}function E(){var O=B(-10,10);O+=parseInt(L.css("left"));if(O>=54){O=54}else{if(O<=34){O=34}}L.animate({left:O},B(1000,3000),K)}function I(O,S){O=O||1;var Q="";var R=S.length-1;var P=0;for(i=0;i<O;i++){P=B(0,R-1);Q+=S.slice(P,P+1)}return Q}function B(Q,P){var R=P-Q;var O=Q+Math.round(Math.random()*R);return parseInt(O)}function G(Q,P){var R=P-Q;var O=Q+Math.random()*R;return parseFloat(O)}};A.fn.coffee.defaults={steams:["jQuery","HTML5","HTML6","CSS2","CSS3","JS","$.fn()","char","short","if","float","else","type","case","function","travel","return","array()","empty()","eval","C++","JAVA","PHP","JSP",".NET","while","this","$.find();","float","$.ajax()","addClass","width","height","Click","each","animate","cookie","bug","Design","Julying","$(this)","i++","Chrome","Firefox","Firebug","IE6","Guitar","Music","攻城师","旅行","王子墨","啤酒"],steamsFontFamily:["Verdana","Geneva","Comic Sans MS","MS Serif","Lucida Sans Unicode","Times New Roman","Trebuchet MS","Arial","Courier New","Georgia"],steamFlyTime:5000,steamInterval:500,steamMaxSize:30,steamHeight:200,steamWidth:300};A.fn.coffee.version="2.0.0"})(Zepto||Jquery);