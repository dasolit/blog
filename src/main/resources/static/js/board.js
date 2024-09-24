let index = {
  init: function() {
    $("#btn-board-save").on("click", ()=>{
      this.save();
    });
    $("#btn-board-delete").on("click", ()=>{
      this.deleteById();
    });
    $("#btn-board-update").on("click", ()=>{
      this.update();
    });
    $("#btn-reply-save").on("click", ()=>{
      this.replySave();
    });
  },
  save:function(){
    let data = {
      title: $("#title").val(),
      content: $("#comment").val(),
    }
    $.ajax({
      type: "POST",
      url:"/api/board",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(response){
      alert("글쓰기가 완료되었습니다.");
      location.href="/";
    }).fail(function(error){
      alert(JSON.stringify(error));
    });
  },
  deleteById:function(){
    let id = $("#id").text();

    $.ajax({
      type: "DELETE",
      url:"/api/board/"+id,
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(response){
      alert("삭제가 완료되었습니다.");
      location.href="/";
    }).fail(function(error){
      alert(JSON.stringify(error));
    });
  },
  update:function(){
    let id = $("#id").val();
    let data = {
      title: $("#title").val(),
      content: $("#comment").val(),
    }
    $.ajax({
      type: "PUT",
      url:"/api/board/"+id,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(response){
      alert("글수정이 완료되었습니다.");
      location.href="/";
    }).fail(function(error){
      alert(JSON.stringify(error));
    });
  },
  replySave:function(){
    let boardId = $("#boardId").val();
    let data = {
      content: $("#reply-content").val(),
    }
    $.ajax({
      type: "POST",
      url:`/api/board/${boardId}/reply`,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function(response){
      alert("댓글 작성이 완료되었습니다.");
      location.href=`/board/${boardId}`;
    }).fail(function(error){
      alert(JSON.stringify(error));
    });
  },
  replyDelete: function(boardId, replyId) {
    $.ajax({
      type: "DELETE",
      url: `/api/board/${boardId}/reply/${replyId}`,
      contentType: "application/json; charset=uft-8",
      dataType: "json"
    }).done(function(response){
      alert("댓글 삭제 성공");
      location.href=`/board/${boardId}`;
    }).fail(function(error){
      alert(JSON.stringify(error));
    });
  }
}
 index.init();