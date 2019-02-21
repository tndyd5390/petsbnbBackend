<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"   %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>펫비앤비 관리자 페이지</title>

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-latest.min.js"></script> 
<script>

	$(function(){
		var array = Array();
		array = '${result}'
		console.log(array);
	})
	
	function test(point_no,status){
		var data = {"point_info_no" : point_no, "status" : status};
		$.ajax({
			url:"updateStatus.do",
			data : data,
			type : "POST",
			dataType:"json",
			success:function(result){
				
				if(result == 1){
					alert("승인취소 되었습니다.")
					location.reload();
				}else{
					alert("승인취소를 실패했습니다.")
					location.reload();
				}
				
			},
			error : function(a,b){
				console.log(a);
				console.log(b);
			}
		})
	}
</script>
</head>
<body id="page-top" >

  <!-- Page Wrapper -->
  <div id="wrapper">

 <%@include file="/comm/nav.jsp" %>
 
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">


        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800" style="margin-top:20px">포인트 지불내역</h1>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">포인트 지불내역</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="font-size:1.2rem">
                  <thead>
                    <tr>
                      <th width="10%">펫시팅 번호</th>
                      <th width="10%">펫시터 이름</th>
                      <th width="8%">거래은행</th>
                      <th width="20%">계좌번호</th>
                      <th width="8%">예금주</th>
                      <th width="10%">지불금액</th>
                      <th  width="10%">승인 확인 날짜</th>
                      <th  width="10%">승인 취소</th>
                    </tr>
                  </thead>
                  <tbody>

                  <c:forEach var="item" items="${result}">
                    <tr>
                      <td>${item.POINT_INFO_NO}</td>
                      <td>${item.PETSITTER_NAME}</td>
                      <td>${item.REFUND_BANK}</td>
                      <td>${item.REFUND_ACCOUNT_NUMBER}</td>
                      <td>${item.REFUND_ACCOUNT_NAME}</td>
                      <td>${item.REFUND_POINT * -1}</td>
                      <td>${item.CHG_DT}</td>
                      <td style="text-align:center">
                      <a href="#" class="btn btn-danger btn-circle" onclick="test('${item.POINT_INFO_NO}','${item.STATUS}')">
                    	<i class="fas fa-trash"></i>
                  	  </a>
                      </td>
                    </tr>
                  </c:forEach>           
                             
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">횞</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>

</body>

</html>
