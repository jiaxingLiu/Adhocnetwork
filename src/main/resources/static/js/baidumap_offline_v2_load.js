
var bdmapcfg = {
  'imgext':'.jpg', //瓦片图的后缀 ------ 根据需要修改，一般是 .png .jpg
  'tiles_dir':'tiles'   //瓦片图的目录，为空默认在 baidumap_v2/tiles/ 目录
  
  //E://项目文件//离线地图//baidumap_apiV2_offline//baidumapv2//tiles
};


var scripts = document.getElementsByTagName("script");
var JS__FILE__ = scripts[scripts.length - 1].getAttribute("src");
bdmapcfg.home = JS__FILE__.substr(0, JS__FILE__.lastIndexOf("/")+1); //地图API主目录, images,tiles都在此目录下
(function(){
  window.BMap_loadScriptTime = (new Date).getTime();
  //加载地图API主文件
  document.write('<script type="text/javascript" src="'+bdmapcfg.home+'baidumap_offline_v2_20160921_min.js"></script>');
})();