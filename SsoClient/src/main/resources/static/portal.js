//GET: $(document).ready(
//		function() {
//
//			// GET REQUEST
//			$("#accessportal").click(function(event) {
//				event.preventDefault();
//				ajaxGet();
//			});
//
//			// DO GET
//			function ajaxGet() {
//				$.ajax({
//					url : "http://localhost:8080/access",
//					success : function(result) {
//						$("#apiResponse").html(result);
//					}
//				});
//			}
//		})

$.ajax({
  url: "ajax.aspx",
  type: "get", //send it through get method
  data: {
    token: 4,
    clientId: "client1"
  },
  success: function(response) {
    //Do Something
  },
  error: function(xhr) {
    //Do Something to handle error
  }
});