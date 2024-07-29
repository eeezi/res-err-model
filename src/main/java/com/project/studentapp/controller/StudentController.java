package com.project.studentapp.controller;

import com.project.studentapp.entity.ApiResponse;
import com.project.studentapp.entity.ErrorCode;
import com.project.studentapp.exception.CustomException;
import com.project.studentapp.exception.InputRestriction;
import com.project.studentapp.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
  private final StudentService studentService;

  // 이름과 성적 입력 -> 저장
  @PostMapping("/student")
  public ApiResponse add(
    @RequestParam("name") String name,
    @RequestParam("grade") int grade
  ) {
    if (grade >= 6) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "grade는 6 이상을 입력할 수 없습니다.", new InputRestriction(6));
    }
    //서비스에 addStudent
    return makeResponse(studentService.addStudent(name, grade));
  }

  // 전체 성적 조회
  @GetMapping("/students")
  public ApiResponse getAll() {return makeResponse(studentService.getAll());}

  // 특정 성적을 입력받아서, 해당 성적의 학생들을 조회
  @GetMapping("/students/{grade}")
  public ApiResponse getGradeStudent(
    @PathVariable("grade") int grade
  ){
    return makeResponse(studentService.get(grade));
  }



  // 복수개의 results를 내려주는 makeResponse()
  public <T> ApiResponse<T> makeResponse(List<T> results) { return new ApiResponse<>(results);}

  // 단수의 result를 내려주는 makeResponse()
  public <T> ApiResponse<T> makeResponse(T result) { return new ApiResponse<>(List.of(result));}

  @ExceptionHandler(CustomException.class)
  public ApiResponse customExceptionHandler(HttpServletResponse response, CustomException customException) {
    return new ApiResponse(customException.getErrorCode().getCode(), customException.getErrorCode().getMessage(), customException.getData());
  }


}
