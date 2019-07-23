/**
 * Created by Raytine on 2019/7/16.
 */
$(function () {
    $("#selectid").change(function () {
        var selectId = $("#selectid").val();
        var url="/weatherservice/cityName/"+selectId;
        window.location.href=url;
    });
})
