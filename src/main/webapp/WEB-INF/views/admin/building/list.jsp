<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
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

            <div class = "row">
                <div class="col-xs-12">
                    <div class="widget-box ui-sortable-handle">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm kiếm</h5>

                            <div class="widget-toolbar">

                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>

                            </div>
                        </div>

                        <div class="widget-body" style= "font-family: 'Times New Roman', Times, serif;">
                            <div class="widget-main">
                                <form:form id="listForm" modelAttribute="modelSearch" action="${buildingListURL}" method="GET">
                                    <div class = "row">
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <label class = "name">Tên tòa nhà</label>
                                                        <form:input class="form-control" path="name"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <label class = "name">Diện tích sàn</label>
                                                        <form:input class="form-control" path="floorArea"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-2">
                                                    <div>
                                                        <label class = "name">Quận hiện có</label>
                                                        <form:select class = "form-control" path="district">
                                                            <form:option value="">---Chọn Quận---</form:option>
                                                            <form:options items="${districtCodes}" />
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-5">
                                                    <div>
                                                        <label class = "name">Phường</label>
                                                        <form:input class="form-control" path="ward"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-5">
                                                    <div>
                                                        <label class = "name">Đường</label>
                                                        <form:input class="form-control" path="street"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Số tầng hầm</label>
                                                        <form:input class="form-control" path="numberOfBasement"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Hướng</label>
                                                        <form:input class="form-control" path="direction"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Hạng</label>
                                                        <form:input class="form-control" path="level"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-3">
                                                    <div>
                                                        <label class = "name">Diện tích từ</label>
                                                        <form:input class="form-control" path="areaFrom"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-3">
                                                    <div>
                                                        <label class = "name">Diện tích đến</label>
                                                        <form:input class="form-control" path="areaTo"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-3">
                                                    <div>
                                                        <label class = "name">Giá thuê từ</label>
                                                        <form:input class="form-control" path="rentPriceFrom"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-3">
                                                    <div>
                                                        <label class = "name">Giá thuê đến</label>
                                                        <form:input class="form-control" path="rentPriceTo"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-5">
                                                    <div>
                                                        <label class = "name">Tên quản lý</label>
                                                        <form:input class="form-control" path="managerName"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-5">
                                                    <div>
                                                        <label class = "name">Điện thoại quản lý</label>
                                                        <form:input class="form-control" path="managerPhone"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-2">
                                                    <security:authorize access="hasRole('MANAGER')">
                                                        <div>
                                                            <label class = "name">Chọn nhân viên phụ trách</label>
                                                            <form:select class = "form-control" path="staffId">
                                                                <form:option value="">---Chọn nhân viên---</form:option>
                                                                <form:options items="${listStaffs}"/>
                                                            </form:select>
                                                        </div>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-6">
                                                    <div>
                                                        <button type="button" class="btn btn-danger" id="btnSearchBuilding">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"></path>
                                                            </svg>
                                                            Tìm kiếm
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <security:authorize access="hasRole('MANAGER')">
                            <div class = "pull-right">
                                <button class = "btn-success" title = "Thêm tòa nhà">
                                    <a href="/admin/building-edit">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                            <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                            <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                        </svg>
                                    </a>
                                </button>
                                <button class = "btn-danger" title = "Xóa tòa nhà" id="btnDeleteBuilding">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>
                                </button>
                            </div>
                        </security:authorize>

                    </div>
                </div>
            </div>

            <!--Bảng tòa nhà-->
<%--            <div class = "row">--%>
<%--                <div class="col-xs-12">--%>
<%--                    <table id="tableList" style = "margin: 3em 0 1.5em;" class="table table-striped table-bordered table-hover">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th class="center">--%>
<%--                                <label class="pos-rel">--%>
<%--                                    <input type="checkbox" class="ace" name="checkList" value="">--%>
<%--                                    <span class="lbl"></span>--%>
<%--                                </label>--%>
<%--                            </th>--%>
<%--                            <th>Tên tòa nhà</th>--%>
<%--                            <th>Địa chỉ</th>--%>
<%--                            <th>Số tầng hầm</th>--%>
<%--                            <th>Tên quản lý</th>--%>
<%--                            <th>Số điện thoại quản lý</th>--%>
<%--                            <th>DT sàn</th>--%>
<%--                            <th>DT trống</th>--%>
<%--                            <th>DT thuê</th>--%>
<%--                            <th>Giá thuê</th>--%>
<%--                            <th>Phí môi giới</th>--%>
<%--                            <th>Phí dịch vụ</th>--%>
<%--                            <th>Thao tác</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <c:forEach var="item" items="${buildingList}">--%>
<%--                            <tr>--%>
<%--                                <td class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace" name="checkBuilding" value="${item.id}">--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </td>--%>

<%--                                <td>${item.name}</td>--%>
<%--                                <td>${item.address}</td>--%>
<%--                                <td>${item.numberOfBasement}</td>--%>
<%--                                <td>${item.managerName}</td>--%>
<%--                                <td>${item.managerPhone}</td>--%>
<%--                                <td>${item.floorArea}</td>--%>
<%--                                <td>${item.emptyArea}</td>--%>
<%--                                <td>${item.rentArea}</td>--%>
<%--                                <td>${item.rentPrice}</td>--%>
<%--                                <td>${item.brokerageFee}</td>--%>
<%--                                <td>${item.serviceFee}</td>--%>
<%--                                <td>--%>
<%--                                    <div class="hidden-sm hidden-xs btn-group">--%>
<%--                                        <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})">--%>
<%--                                            <i class="ace-icon fa fa-check bigger-120"></i>--%>
<%--                                        </button>--%>

<%--                                        <a class="btn btn-xs btn-info", title="Sửa tòa nhà" href="/admin/building-edit-${item.id}">--%>
<%--                                            <i class="ace-icon fa fa-pencil bigger-120"></i>--%>
<%--                                        </a>--%>

<%--                                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${item.id})">--%>
<%--                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>--%>
<%--                                        </button>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>

<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--            </div>--%>

            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="buildingList.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${buildingListURL}" partialList="true" sort="external"
                                       size="${buildingList.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${buildingList.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column property="name" title="Tên tòa nhà" headerClass="center"/>
                            <display:column property="address" title="Địa chỉ" headerClass="center"/>
                            <display:column property="numberOfBasement" title="Số tầng hầm" headerClass="center"/>
                            <display:column property="managerName" title="Tên quản lý" headerClass="center"/>
                            <display:column property="managerPhone" title="Số điện thoại" headerClass="center"/>
                            <display:column property="floorArea" title="Diện tích sàn" headerClass="center"/>
                            <display:column property="emptyArea" title="Diện tích trống" headerClass="center"/>
                            <display:column property="rentArea" title="Diện tích thuê" headerClass="center"/>
                            <display:column property="brokerageFee" title="Phí môi giới" headerClass="center"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${tableList.id})">
                                            <i class="ace-icon fa fa-check bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                    <a class="btn btn-xs btn-info", title="Sửa tòa nhà" href="/admin/building-edit-${tableList.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" title="Xóa tòa nhà" onclick="deleteBuilding(${tableList.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">---Chọn---</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<script>
    function assignmentBuilding(buildingId){
        $('#assignmentBuildingModal').modal();
        loadStaff(buildingId);
        $('#buildingId').val(buildingId);
    };
    $('#btnAssignmentBuilding').click(function(e){
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(staffs != '')  assignment(data);
        else {
            window.location.href="<c:url value="/admin/building-list?message=error"/>"
        }
    });
    function assignment(data){
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/" + "assignment",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                console.log("OK");
                window.location.href="<c:url value="/admin/building-list?message=success"/>"
            },
            error: function(response){
                window.location.href="<c:url value="/admin/building-list?message=error"/>"
                console.log("failed");
            }
        });
    }
    function loadStaff(buildingId){
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + '/staffs',
            // data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                var row = '';
                $.each(response.data, function (index,item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type = "checkbox" value="'+item.staffId+'" id="checkbox_'+item.staffId+'" class = "check-box-element " ' + item.checked+ '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function(response){
                console.log("failed");
                window.location.href="<c:url value="/admin/building-list?message=error"/>"
            }
        });
    };
    $('#btnSearchBuilding').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    });
    function deleteBuilding(id){
        var buildingId = [id];
        deleteBuildings(buildingId);
    };
    $('#btnDeleteBuilding').click(function (e){
        e.preventDefault();
        var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        deleteBuildings(buildingIds);
    });
    function deleteBuildings(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                console.log("OK");
                window.location.href="<c:url value="/admin/building-list?message=success"/>"
            },
            error: function(response){
                console.log("failed");
            }
        });
    }
</script>

<!-- <![endif]-->

</body>
</html>
