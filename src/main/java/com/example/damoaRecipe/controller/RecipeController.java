package com.example.damoaRecipe.controller;

import com.example.damoaRecipe.entity.Recipe;
import com.example.damoaRecipe.form.RecipeForm;
import com.example.damoaRecipe.form.ReviewForm;
import com.example.damoaRecipe.repository.RecipeRepository;
import com.example.damoaRecipe.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/recipe/new") //새로은 레시피를 입력하는 폼
    public String recipeForm(){
        return "/recipe/new";
    }

    @PostMapping("/recipe/create")//사용자가 입력한 레시피를 생성, 데이터베이스에 저장
    public String recipeCreate(RecipeForm form){
        log.info(form.toString());
        Recipe recipe=form.toEntity(); //RecipeForm을 레시피 엔터티로 변환
        log.info(recipe.toString());
        Recipe saved=recipeRepository.save(recipe); //변환된 레시피 엔터티를 데이터베이스에 저장
        log.info(saved.toString());
        return "redirect:/recipe/"+saved.getRecipeId(); //저장된 레시피의 ID를 이용해 해당 레시피의 상세 페이지로 리다이렉트
    }

    @GetMapping("/recipe/{recipeId}/like") //특정 레시피에 좋아요 표시
    public String getLike(@PathVariable Long recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId); //데이터베이스에서 해당 레시피를 찾음
        Recipe recipe1 = recipe.get();
        recipe1.recipeLike();
        Recipe saved=recipeRepository.save(recipe1); //번경된 레시피를 데이터베이스에 저장
        return "redirect:/recipe/"+saved.getRecipeId(); //저장된 레시피의 ID를 이용해 해당 레시피의 상세 페이지로 리다이렉트

    }


    @GetMapping("/recipe/{recipeId}") //특정 레시피의 상세 정보와 해당 레시피에 대한 리뷰를 보여줌
    public String show(@PathVariable Long recipeId, Model model){
        log.info("recipeId ="+recipeId);
        Recipe recipeEntity = recipeRepository.findById(recipeId).orElse(null); //데이터베이스에서 특정 레시피를 찾음
        List<ReviewForm> reviewForm=reviewService.review(recipeId); //찾은 레시피 정보와 해당 레시피에 대한 리뷰를 뷰에 전달
        model.addAttribute("recipe",recipeEntity); //찾은 레시피 정보를 뷰에 전달
        model.addAttribute("reviewForm",reviewForm); //찾은 리뷰 정보를 뷰에 전달
        return "recipe/show";
    }

    @GetMapping("/recipe") //모든 레시피의 목록을 보여줌
    public String index(Model model){
        ArrayList<Recipe> recipeEntityList = recipeRepository.findAll(); //데이터베이스에서 모든 레시피를 조회
        model.addAttribute("recipeList",recipeEntityList); //조회돈 레시피 목록을 뷰에 전달
        return "recipe/index"; //레시피 목록을 표시
    }

    @GetMapping("/recipe/{recipeId}/edit") //특정 레시피의 정보를 수정
    public String edit(@PathVariable Long recipeId, Model model){
        Recipe recipeEntity=recipeRepository.findById(recipeId).orElse(null); //데이터베이스에서 수정할 레시피를 찾음
        model.addAttribute("recipe",recipeEntity); //찾은 레시피 정보를 뷰에 전달
        return "recipe/edit"; //수정폼음 표시
    }

    @GetMapping("/recipe/{recipeId}/update") //사용자가 수정한 레시피 정보를 보여줌
    public String update(@PathVariable Long recipeId,RecipeForm form){
        log.info(form.toString());
        Recipe recipeEntity=form.toEntity();  //RecipeForm을 레시피 엔터티(Recipe)로 변환
        log.info(recipeEntity.toString());
        Recipe target = recipeRepository.findById(recipeId).orElse(null);
        if (target != null) {
            target.patch(recipeEntity);
            recipeRepository.save(target);
        }

        return "redirect:/recipe/" + recipeEntity.getRecipeId();

    }


    @GetMapping("recipe/{recipeId}/delete") //특정 레시피 삭제
    public String delete(@PathVariable Long recipeId, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");
        Recipe target=recipeRepository.findById(recipeId).orElse(null); // 데이터베이스에서 삭제할 레시피를 찾음
        log.info(target.toString());
        if(target!=null){
            target.recipeStatus = 0;  //레시피의 상태를 0으로 변경,변경된 레시피를 데이터베이스에 저장
            recipeRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!!"); //삭제 성공 메시지 설정
        }
        return "redirect:/recipe";
    }

}
