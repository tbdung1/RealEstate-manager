<%--
  Created by IntelliJ IDEA.
  User: Samkonku
  Date: 4/25/2024
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                <li class="active">Quản lý khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Danh sách khách hàng
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
                                <form:form id="listForm" modelAttribute="modelSearch" action="${customerListURL}" method="GET">
                                    <div class = "row">
                                        <div class="form-group">
                                            <div class = "col-xs-12">
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Tên khách hàng</label>
                                                        <form:input class="form-control" path="fullName"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Di động</label>
                                                        <form:input class="form-control" path="phone"/>
                                                    </div>
                                                </div>
                                                <div class = "col-xs-4">
                                                    <div>
                                                        <label class = "name">Email</label>
                                                        <form:input class="form-control" path="email"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class = "col-xs-4">
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
                                                        <button type="button" class="btn btn-danger" id="btnSearchCustomer">
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
                        <div class = "pull-right">
                        <security:authorize access="hasRole('MANAGER')">
                            <button class = "btn-success" title = "Thêm khách hàng">
                                <a href="/admin/customer-edit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                        <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                    </svg>
                                </a>
                            </button>
                            <button class = "btn-danger" title = "Xóa khách hàng" id="btnDeleteCustomer">
                                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                    <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                </svg>
                            </button>
                        </security:authorize>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="customerList.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${customerListURL}" partialList="true" sort="external"
                                       size="${customerList.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${customerList.maxPageItems}"
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
                            <display:column property="fullName" title="Tên khách hàng" headerClass="center"/>
                            <display:column property="phone" title="Di động" headerClass="center"/>
                            <display:column property="email" title="Số điện thoại" headerClass="center"/>
                            <display:column property="demand" title="Nhu cầu" headerClass="center"/>
                            <display:column property="createdBy" title="Người thêm" headerClass="center"/>
                            <display:column property="createdDate" title="Ngày thêm" headerClass="center"/>
                            <display:column property="status" title="Tình trạng" headerClass="center"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                    </security:authorize>
                                    <a class="btn btn-xs btn-info", title="Sửa thông tin" href="/admin/customer-edit-${tableList.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick="deleteCustomer(${tableList.id})">
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
<div class="modal fade" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
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
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentCustomer(customerId){
        $('#assignmentCustomerModal').modal();
        loadStaff(customerId);
        $('#customerId').val(customerId);
    };
    $('#btnAssignmentCustomer').click(function(e){
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(staffs != '')  assignment(data);
        else {
            window.location.href="<c:url value="/admin/customer-list?message=error"/>"
        }
    });
    function assignment(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}/" + "assignment",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                console.log("OK");
                window.location.href="<c:url value="/admin/customer-list?message=success"/>"
            },
            error: function(response){
                window.location.href="<c:url value="/admin/customer-list?message=error"/>"
                console.log("failed");
            }
        });
    }
    function loadStaff(customerId){
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + '/staffs',
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
                window.location.href="<c:url value="/admin/customer-list?message=error"/>"
            }
        });
    };
    $('#btnSearchCustomer').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    });
    function deleteCustomer(id){
        var customerId = [id];
        deleteCustomers(customerId);
    };
    $('#btnDeleteCustomer').click(function (e){
        e.preventDefault();
        var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
            return $(this).val();
        }).get();
        deleteCustomers(customerIds);
    });
    function deleteCustomers(data){
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/" + data,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                console.log("OK");
                window.location.href="<c:url value="/admin/customer-list?message=success"/>"
            },
            error: function(response){
                console.log("failed");
                window.location.href="<c:url value="/admin/customer-list?message=error"/>"
            }
        });
    }
</script>
</body>
</html>
