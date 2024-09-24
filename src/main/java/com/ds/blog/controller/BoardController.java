package com.ds.blog.controller;

import com.ds.blog.model.Board;
import com.ds.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping({"", "/"})
  public String index(
      Model model,
      @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable
  ){
    model.addAttribute("boards", boardService.글목록(pageable));
    return "index";
  }

  @GetMapping("/board/{id}")
  public String findById(@PathVariable Long id, Model model){
    Board board = boardService.글상세보기(id);
    model.addAttribute("board", board);
    return "board/detail";
  }
  // USER 권한 필요
  @GetMapping("/board/saveForm")
  public String saveForm(){
    return "board/saveForm";
  }

  @GetMapping("/board/{id}/updateForm")
  public String updateForm(@PathVariable Long id, Model model){
    model.addAttribute("board", boardService.글상세보기(id));
    return "board/updateForm";
  }


}
