package com.example.backend.controller;

import com.example.backend.apiPayload.ApiResponse;
import com.example.backend.dto.LandmarkResponseDTO.LandmarkFindDTO;
import com.example.backend.dto.LandmarkResponseDTO.LandmarkPreViewDTO;
import com.example.backend.model.enums.Category;
import com.example.backend.service.LandmarkService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/landmarks")
public class LandmarkController {

  private final LandmarkService landmarkService;

  @GetMapping("/fetch-landmarks")
  public String fetchLandmarks() {
    landmarkService.fetchAndSaveLandmarks();
    return "Landmarks have been fetched and saved successfully!";
  }

  @Operation(summary = "랜드마크 검색 API", description = "랜드마크 리스트 페이지에서의 검색 API입니다. 검색어 RequestParam 입니다!")
  @GetMapping("/search")
  public ApiResponse<List<LandmarkPreViewDTO>> searchLandmark(@RequestParam("keyword") String keyword) {
    List<LandmarkPreViewDTO> result = landmarkService.searchLandmark(keyword);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "랜드마크 목록 조회 API", description = "랜드마크 리스트 페이지에서의 목록 조회 API입니다. category(nature, history, culture)")
  @GetMapping("")
  public ApiResponse<List<LandmarkPreViewDTO>> getLandmarks(@RequestParam(name = "category", required = false) String category,
      @RequestParam(name = "interest", required = false) List<Category> interests) {
    List<LandmarkPreViewDTO> result = landmarkService.getLandmarks(category, interests);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "랜드마크 카테고리 검색 API", description = "랜드마크 리스트 페이지에서의 카테고리 검색 API입니다. 카테고리 RequestParam 입니다!")
  @GetMapping("/category")
  public ApiResponse<List<LandmarkPreViewDTO>> categoryLandmark(@RequestParam("category") Category category) {
    List<LandmarkPreViewDTO> result = landmarkService.categoryLandmark(category);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "랜드마크 발견 페이지 조회 API", description = "랜드마크 발견 페이지 조회 API입니다. 랜드마크 아이디(landmarkId) PathVariable 입니다! ")
  @GetMapping("/find/{landmarkId}")
  public ApiResponse<LandmarkFindDTO> getLandmarkFind(@PathVariable("landmarkId") Long landmarkId) {
    LandmarkFindDTO result = landmarkService.getLandmarkFind(landmarkId);
    return ApiResponse.onSuccess(result);
  }

  @Operation(summary = "랜드마크 위치기반 조회 API", description = "내 주변 좌표를 기반으로 랜드마크를 조회하는 기능입니다.")
  @GetMapping("/close")
  public ApiResponse<List<LandmarkPreViewDTO>> getCloseLandmarks(@RequestParam(name = "mapX") double mapX, @RequestParam(name = "mapY") double mapY) {
    List<LandmarkPreViewDTO> result = landmarkService.getCloseLandmarks(mapX, mapY);
    return ApiResponse.onSuccess(result);
  }

}
