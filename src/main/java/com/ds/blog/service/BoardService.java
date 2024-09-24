package com.ds.blog.service;

import com.ds.blog.model.Board;
import com.ds.blog.model.Reply;
import com.ds.blog.model.User;
import com.ds.blog.repository.BoardRepository;
import com.ds.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  private final ReplyRepository replyRepository;

  @Transactional
  public void 글쓰기(Board board, User user) {
    board.setCount(0);
    board.setUser(user);
    boardRepository.save(board);
  }

  @Transactional(readOnly = true)
  public Page<Board> 글목록(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Board 글상세보기(Long id) {
    return boardRepository.findById(id)
        .orElseThrow(() -> {
          return new IllegalArgumentException("글 상세보기 실패: Board ID를 찾을 수 없습니다.");
        });
  }

  @Transactional
  public void 삭제하기(Long id) {
    boardRepository.deleteById(id);
  }

  @Transactional
  public void 수정하기(Long id, Board requestBoard) {
    Board board = boardRepository.findById(id)
        .orElseThrow(() -> {
          return new IllegalArgumentException("글 찾기 실패: Board ID를 찾을 수 없습니다.");
        });
    board.setTitle(requestBoard.getTitle());
    board.setContent(requestBoard.getContent());
  }

  @Transactional
  public void 댓글쓰기(User user, Long boardId, Reply reply) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> {
          return new IllegalArgumentException("댓글 실패: 게시글 ID를 찾을 수 없습니다.");
        });
    reply.setUser(user);
    reply.setBoard(board);
    replyRepository.save(reply);
  }

  @Transactional
  public void 댓글삭제(Long replyId) {
    replyRepository.deleteById(replyId);
  }
}
