<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Form 태그를 이용한 FileUpload
			</div>
			<div class="card-body">
	            <form method="post" enctype="multipart/form-data" action="fileupload">
	               <div class="form-group">
	                  <label for="title">File Title</label> 
	                  <input type="text" class="form-control" id="title" name="title" placeholder="파일 설명">
	               </div>
	               <div class="form-group">
	                  <label for="desc">File Description</label> 
	                  <input type="text" class="form-control" id="desc" name="desc" placeholder="파일 설명">
	               </div>
	               <div class="form-group">
	                   <label for="attach">Example file input</label>
	                   <input type="file" class="form-control-file" id="attach" name="attach">
	               </div>
	               <button class="btn btn-info btn-sm">파일 업로드</button>
	            </form>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>