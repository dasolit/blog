package com.ds.blog.controller.api;

import com.ds.blog.auth.PrincipalDetail;
import com.ds.blog.dto.ResponseDto;
import com.ds.blog.model.Board;
import com.ds.blog.model.Reply;
import com.ds.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

  private final BoardService boardService;

  @PostMapping("/api/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal
      PrincipalDetail principalDetail) {
    boardService.글쓰기(board, principalDetail.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable Long id) {
    boardService.삭제하기(id);
    return new ResponseDto<>(HttpStatus.OK.value(), 1);
  }

  @PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable Long id,@RequestBody Board requestBoard) {
    boardService.수정하기(id, requestBoard);
    return new ResponseDto<>(HttpStatus.OK.value(), 1);
  }

  @PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(
      @PathVariable Long boardId,
      @RequestBody Reply reply,
      @AuthenticationPrincipal
  PrincipalDetail principalDetail) {
    boardService.댓글쓰기(principalDetail.getUser(), boardId ,reply);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
  public ResponseDto<Integer> deleteReply(@PathVariable Long boardId, @PathVariable Long replyId) {
    boardService.댓글삭제(replyId);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

}
