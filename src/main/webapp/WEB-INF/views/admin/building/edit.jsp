<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-content">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1>
                        Danh sách tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <!-- <div class = "row">
                    <div class="col-xs-12">
                    </div>
                </div> -->

                <!--Bảng danh sách-->
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form id="listForm" modelAttribute="buildingEdit" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">
                                <di class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9"> <!-- xs chứ ko phải sx-->
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                </di>
                                <di class = "form-group">
                                    <label class = "col-xs-3">Quận</label>
                                    <div class = "col-xs-2">
                                        <form:select class = "form-control" path="district">
                                            <form:option value="">---Chọn Quận---</form:option>
                                            <form:options items="${districtCodes}" />
                                        </form:select>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="ward"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="street"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="structure"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="floorArea"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="level"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentArea"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentPrice"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentPriceDescription"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="serviceFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="carFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="motoFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="overtimeFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="electricityFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="deposit"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="payment"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="rentTime"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="decorationTime"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="managerName"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="managerPhone"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="brokerageFee"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class = "col-xs-9">
                                        <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="note"/>
                                    </div>
                                </di>
                                <div class="form-group">
                                    <label class="col-xs-3">Ảnh tòa nhà</label>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty buildingEdit.image}">
                                            <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty buildingEdit.image}">
                                            <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3"> </div>
                                    <input class="col-sm-9 no-padding-right" type="file" id="uploadImage"/>
                                </div>

                                <di class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
                                            <button type="button" class="btn btn-success" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                        <c:if test="${empty buildingEdit.id}">
                                            <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                            <button type="button" class="btn btn-success" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </div>
                                </di>
                                <form:hidden path="id" id="buildingId" />
                            </form>
                        </div>
                    </form:form>


                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<script>
    var imageBase64 = '';
    var imageName = '';

    $('#btnAddOrUpdateBuilding').click(function(){
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
            if(v.name != 'typeCode')
                data['' + v.name + ''] = v.value;
            else
                typeCode.push(v.value);
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        $('#loading_image').show();
        data['typeCode'] = typeCode;
        if(typeCode.length != 0 && data['name'] != ""){
            addOrUpdateBuilding(data);
        }
        else{
            if(data['name'] == "" && typeCode.length == 0){
                window.location.href="<c:url value="/admin/building-edit?typeCode=required&name=required"/>"
            }
            else if (typeCode.length == 0){
                window.location.href="<c:url value="/admin/building-edit?typeCode=required"/>"
            }
            else{
                window.location.href="<c:url value="/admin/building-edit?name=required"/>"
            }
            }
    });
    function addOrUpdateBuilding(data){
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                window.location.href="<c:url value="/admin/building-list?message=success"/>"
            },
            error: function(response){
                window.location.href="<c:url value="/admin/building-list?message=error"/>"
            }
        });
    }
    $('#btnCancel').click(function (){
        window.location.href="/admin/building-list"
    })
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
