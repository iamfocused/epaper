<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, target-densitydpi=device-dpi">
<title>EZ-KANPOD</title>
<!--<link href="/static/css/jquery.mobile-1.3.2.css" rel="stylesheet" />-->
<link href="/static/themes/jquery.mobile-1.4.5.min.css" rel="stylesheet" type="text/css" />
<!--<link href="/static/css/jquery.mobile.theme-1.3.2.css" rel="stylesheet" type="text/css" />
<link href="/static/css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" />-->
<link rel="stylesheet" href="/static/themes/bladecolor.min.css" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/static/themes/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="/static/themes/bootstrap-theme.min.css">
<link rel="stylesheet" href="/static/themes/jqm-demos.css">
<link rel="stylesheet" href="/static/themes/jquery.mobile.icons.min.css">
<link rel="stylesheet" href="/static/themes/jquery.mobile.structure-1.4.5.min.css">
<link href="/static/css/all.min.css" rel="stylesheet" type="text/css">
<link href="/static/css/googleicon.css" rel="stylesheet">
<link href="/static/css/site.css" rel="stylesheet" />
<script src="/static/vendor/jquery/dist/jquery.js"></script>
<script src="/static/lib/jquery.mobile-1.4.5.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/lib/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.qrcode.js"></script>
<script type="text/javascript" src="/static/js/qrcode.min.js"></script>
<script type="text/javascript" src="/static/js/jsbarcode.all.min.js"></script>
<link rel="shortcut icon" href="/static/favicon.ico">
<style>
@media screen and (min-width:0px) {
.row {
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 0px;
}
}

@media screen and (min-width:667px) {
.row {
	margin-left: 16px;
	margin-right: 16px;
	margin-top: 32px;
}
}

@media screen and (min-width:992px) {
.row {
	margin-left: 16px;
	margin-right: 16px;
	margin-top: 32px;
}
}
 @media screen and (min-width:1200px) {
.row {
	margin-left: 64px;
	margin-right: 64px;
	margin-top: 32px;
}
 @media screen and (min-width:1600px) {
.row {
 margin-left: 72px;
 margin-right: 72px;
 margin-top: 32px;
}
 @media screen and (min-width:1900px) {
.row {
 margin-left: 156px;
 margin-right: 600px;
 margin-top: 32px;
}
}
</style>
</head>
<body>
<div data-role="page" id="appshome" data-theme="c" style="background-color: #eceff1; bottom:0px; text-shadow: 0 0 0;">
  <div data-role="header" data-theme="b" data-position="fixed" style="background-color:#ffffff; border-top-width: 0px; border-bottom-width:1px;">
    <div class="ui-grid-a">
      <div class="ui-block-a">
        <h3 style="margin-left: 10px; margin-top:10px; margin-bottom:8px;"><a href="#panelgrid" style="color:#546e7a"><i class="far fa-bars"></i></a></h3>
      </div>
      <div class="ui-block-b">
        <h3 style="margin-left: 10px; margin-right: 10px; margin-top: 8px; margin-bottom:4px; text-shadow: 0 0px; color:rgba(15,15,15,1.00); text-align:right"><img src="/static/images/kanezpodlogo.png" alt="KANPOD LOGO" height="24px" style="margin-left: 24px; margin-bottom:2px"></h3>
      </div>
    </div>
    <!-- /grid-a --> 
  </div>
  <div data-role="content" style="padding: 2px 2px;">
    <div class="row" style="margin-top: 48px;">
      <div class="col-sm-12 col-xs-12 col-md-3 col-lg-3" style="padding: 3px 3px;">
        <p align="left" style="color: #607d8b"><i class="far fa-fw fa-microchip" style="margin-right: 8px;"></i>屏幕选择</p>
        <div class="ui-field-contain ui-alt-icon ui-nodisc-icon">
          <select name="select-custom-1" id="select-custom-1" data-theme="b" data-native-menu="false">
            <#list screenDeviceList as data>
                 <option value="${data.deviceIp}">${data.deviceDesc}(${data.deviceIp})</option>
            </#list>
          </select>
        </div>
      </div>
      <div class="col-sm-12 col-xs-12 col-md-3 col-lg-3" style="padding: 3px 3px;">
        <p align="left" style="color: #607d8b"><i class="far fa-fw fa-folder-times" style="margin-right: 8px;"></i>清空旧屏幕数据?</p>
        <div class="ui-field-contain">
          <form>
            <label>
              <input id="emptyFolders" type="checkbox" data-theme="b" name="checkbox-0">
              清空</label>
          </form>
        </div>
      </div>
		
		<div class="col-sm-12 col-xs-12 col-md-3 col-lg-3" style="padding: 3px 3px;">
        <p align="left" style="color: #607d8b"><i class="far fa-fw fa-stopwatch" style="margin-right: 8px;"></i>翻页间隔</p>
        <div class="ui-field-contain">
          <form>
            <label>
              <input id="refresh_interval" data-clear-btn="true" type="number" data-theme="b" name="interval-0" style="font-size: 15px; height: 37px" placeholder="数据翻页间隔时间，单位为秒">
              </label>
          </form>
        </div>
      </div>
      <div class="col-sm-12 col-xs-12 col-md-3 col-lg-3" style="padding: 3px 3px;">
        <p align="left" style="color: #607d8b"><i class="far fa-fw fa-file-import" style="margin-right: 8px;"></i>文件选择，pdf或png</p>
        <div class="ui-field-contain">
          <form  id="fileForm"  method="POST" enctype="multipart/form-data">
            <input type="file" data-clear-btn="true" data-theme="b" style="font-size: 15px; height: 38px" name="file" id="file" value="">
          </form>
        </div>
      </div>
    </div>
    <div class="row" style="margin-top: 48px;">
      <p align="center"><a id="upload" onclick="upload()" href="#" class="ui-btn ui-btn-b  ui-btn-inline ui-corner-all" style="font-weight: 800; font-size: 18px;"><i class="fas fa-fw fa-cloud-upload-alt" style="margin-right: 15px; color: #4caf50"></i><i id="execute">执行</i></a></p>
    </div>
    
    <!-- /content -->
    <div data-role="panel" id="panelgrid" data-position="left" data-display="overlay">
      <div class="jqm-block-content" style="margin: -14px -14px; margin-bottom: 0px; padding: 9px 9px; padding-top: 38px; background-color: #263238;   border:none; min-height: 0px; border-radius: 0em;">
        <p align="center" style="margin-bottom:32px; text-shadow:1px 1px 3px rgba(74,74,74,0.58)"><img src="/static/images/kanezpodlogo.png" height="32px"></p>
         
      </div>
      <ul data-role="listview" data-theme="b"  class="ui-nodisc-icon ui-alt-icon" style="margin-top:0px;">
        <li><a href="#"
                           style=" font-weight: 300"><i class="far fa-fw fa-tablet-android" style="margin-right: 8px; color: #78909c"></i>屏幕注册</a></li>
        <li><a href="#"
                           style="  font-weight: 300"><i class="far fa-fw fa-cloud-upload-alt
" style="margin-right: 8px; color: #78909c"></i>上传内容</a></li>
        <li><a href="#"
                           style=" font-weight: 300"><i class="far fa-fw fa-sliders-h-square
" style="margin-right: 8px; color: #78909c"></i>系统设置</a></li>
      </ul>
		
		<div class="row" style="margin: 0px 0px; margin-top: 128px;"><p align="center" style="font-weight: 300; color: #b0bec5">EZ <b style="font-weight: 900">KANPOD</b><sup style="font-size: 50%"> TM</sup></p>
		<p align="center" style="font-size: 8px; color: #cfd8dc">powered by <b>eorion</b><br>SW:Ver 0.1</p></div>
    </div>
  </div>
</div>
<!-- /page -->


<script>
/*上传文件*/
function upload(){

    //$('#upload').attr("style", "background-color:grey")
    $('#upload').css("pointer-events", "none");
    $('#execute').text("正在上传，请耐心等待...")
    var refresh_interval = $('#refresh_interval').val();
    if(($('#refresh_interval').val() == "" || $('#refresh_interval').val() <= 0 )){//默认刷新时间10s
        refresh_interval = 10;
    }
    $.ajax({
        url: "/v1/upload/"+$("#select-custom-1 option:selected").val()+"/"+$('#emptyFolders').is(':checked')+"/"+refresh_interval,
        type: "POST",
        cache: false,
        data: new FormData($('#fileForm')[0]),
        processData: false,
        contentType: false,
        success: function(data){
            alert('上传成功');
        },
        error: function(data){
            alert(JSON.parse(data.responseText)['message'])
        },
        complete: function(){
           $('#upload').css('pointer-events','');
           $('#execute').text("执行")
        }
	});
}


</script>



</body>
</html>