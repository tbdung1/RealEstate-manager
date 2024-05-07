<%--
  Created by IntelliJ IDEA.
  User: Samkonku
  Date: 4/25/2024
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thêm khách hàng</title>
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
                    <li class="active">Thêm khách hàng</li>
                </ul><!-- /.breadcrumb -->


            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1>
                        Thông tin khách hàng
                    </h1>
                </div><!-- /.page-header -->

                <!-- <div class = "row">
                    <div class="col-xs-12">
                    </div>
                </div> -->

                <!--Bảng danh sách-->
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form id="listForm" modelAttribute="customerEdit" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">
                                <di class="form-group">
                                    <label class="col-xs-3">Tên khách hàng</label>
                                    <div class="col-xs-9"> <!-- xs chứ ko phải sx-->
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Số điện thoại</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="customerPhone"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Email</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="email"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Tên công ty</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="companyName"/>
                                    </div>
                                </di>
                                <di class="form-group">
                                    <label class="col-xs-3">Nhu cầu</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="demand"/>
                                    </div>
                                </di>
                                <di class = "form-group">
                                    <label class = "col-xs-3">Trạng thái</label>
                                    <div class = "col-xs-2">
                                        <form:select class = "form-control" path="status">
                                            <form:option value="">---Chọn trạng thái---</form:option>
                                            <form:options items="${listStatus}" />
                                        </form:select>
                                    </div>
                                </di>

                                <di class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty customerEdit.id}">
                                            <button type="button" class="btn btn-success" id="btnAddOrUpdateCustomer">Cập nhật khách hàng</button>
                                            <button type="button" class="btn btn-success" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                        <c:if test="${empty customerEdit.id}">
                                            <button type="button" class="btn btn-success" id="btnAddOrUpdateCustomer">Thêm khách hàng</button>
                                            <button type="button" class="btn btn-success" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                    </div>
                                </di>
                                <form:hidden path="id" id="customerId" />
                            </form>
                        </div>
                    </form:form>


                </div>
            </div><!-- /.page-content -->
            <c:forEach var="item" items="${transactionTypes}">
                <div class="col-xs-12">
                    <div class="col-sm-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}', ${customerEdit.id})">
                            <i class="bi bi-plus-circle-fill"></i>ADD
                        </button>
                    </div>
                    <c:if test="${item.key == 'CSKH'}">
                    <div class="col-xs-12">
                        <table id="tableList1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Ngày sửa</th>
                                <th>Người sửa</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${typeCSKH}">
                                <tr>
                                    <td>${item.createdDate}</td>
                                    <td>${item.createdBy}</td>
                                    <td>${item.modifiedDate}</td>
                                    <td>${item.modifiedBy}</td>
                                    <td>${item.transactionDetail}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success" title="Sửa giao dịch" onclick="updateTransaction(${item.id})">
                                                <i class="ace-icon fa fa-check bigger-120"></i>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button class="btn btn-xs btn-danger" title="Xóa giao dịch" onclick="deleteTransaction(${item.id})">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </button>
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    </c:if>
                    <c:if test="${item.key == 'DDX'}">
                        <div class="col-xs-12">
                            <table id="tableList2" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="item" items="${typeDDX}">
                                    <tr>
                                        <td>${item.createdDate}</td>
                                        <td>${item.createdBy}</td>
                                        <td>${item.modifiedDate}</td>
                                        <td>${item.modifiedBy}</td>
                                        <td>${item.transactionDetail}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-success" title="Sửa giao dịch" onclick="updateTransaction(${item.id})">
                                                    <i class="ace-icon fa fa-check bigger-120"></i>
                                                </button>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <button class="btn btn-xs btn-danger" title="Xóa giao dịch" onclick="deleteTransaction(${item.id})">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </button>
                                                </security:authorize>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div><!-- /.main-content -->
    <div class="modal fade" id="transactionTypeModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Nhập giao dịch</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group has-success">
                        <label class="col-sx-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                        <div class="col-xs-12 col-sm-9">
                            <span class="block input-icon input-icon-right">
                                <input type="text" id="transactionDetail" class="width-100">
                            </span>
                        </div>
                    </div>
                    <input type="hidden" id="customerId" name="customerId" value="">
                    <input type="hidden" id="code" name="code" value="">
                    <input type="hidden" id="id" name="id" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="btnAddUpdateTransaction">Thêm giao dịch</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
            </div>

        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<script>
    function transactionType(code, customerId){
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }
    function updateTransaction(id){
        $('#transactionTypeModal').modal();
        $('#id').val(id);
    }
    $('#btnAddUpdateTransaction').click(function(e){
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['transactionDetail'] = $('#transactionDetail').val();
        data['id'] = $('#id').val();
        if(data['transactionDetail'] != '') addOrUpdateTransaction(data);
        else {
            window.location.href="<c:url value="/admin/customer-edit?transactionDetail=required"/>"
        }
    });
    function addOrUpdateTransaction(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}/" + "transaction",
            dataType: "JSON",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                var url = '/admin/customer-edit-' + response + '?message=success';
                // Chuyển hướng trình duyệt đến URL mới
                window.location.href = url;
            },
            error: function(response){
                var url = '/admin/customer-edit-' + response + '?message=error';
                // Chuyển hướng trình duyệt đến URL mới
                window.location.href = url;
            }
        });
    }
    $('#btnAddOrUpdateCustomer').click(function(){
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function(i, v){
                data['' + v.name + ''] = v.value;
        });
        if(data['name'] != "" && data['customerPhone']){
            addOrUpdateCustomer(data);
        }
        else{
            if(data['name'] == "" && data['customerPhone'] == ""){
                window.location.href="<c:url value="/admin/customer-edit?customerPhone=required&name=required"/>"
            }
            else if (data['customerPhone'] == ""){
                window.location.href="<c:url value="/admin/customer-edit?customerPhone=required"/>"
            }
            else{
                window.location.href="<c:url value="/admin/customer-edit?name=required"/>"
            }
        }
    });
    function addOrUpdateCustomer(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function(response){
                window.location.href="<c:url value="/admin/customer-list?message=success"/>"
            },
            error: function(response){
                window.location.href="<c:url value="/admin/customer-list?message=error"/>"
            }
        });
    }
    $('#btnCancel').click(function (){
        window.location.href="/admin/customer-list"
    })
    function deleteTransaction(id){
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}/" + "transaction/" + id,
            data: JSON.stringify(id),
            contentType: "application/json",
            success: function(response){
                alert("Đã xóa giao dịch!");
                <%--window.location.href="<c:url value="/admin/customer-?message=success"/>"--%>
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
