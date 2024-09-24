<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container-fluid mt-3">
    <form>
        <div class="mb-3 mt-3">
            <input type="text" class="form-control" id="title" placeholder="Enter username">
        </div>

        <div class="mb-3 mt-3">
            <textarea class="form-control summernote" id="comment" rows="3"></textarea>
        </div>

    </form>
    <button id="btn-board-save" class="btn btn-primary">글쓰기 완료</button>
</div>
<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300,
    toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link', 'picture', 'video']],
      ['view', ['fullscreen', 'codeview', 'help']]
    ]
  });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


