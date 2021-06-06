package com.example.login_register_demo.controller;

import com.example.login_register_demo.model.Note;
import com.example.login_register_demo.model.UserEntity;
import com.example.login_register_demo.repository.UserEntityReporisoty;
import com.example.login_register_demo.repository.UserRepository;
import com.example.login_register_demo.service.NoteService;
import com.example.login_register_demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private NoteService noteService;

    @Autowired
    UserEntityReporisoty userRepository;


    @GetMapping("/login")
    public  String showFromLogin(){
        return "/loginPage";
    }
    @GetMapping("/home")
    public  ModelAndView test(){
        List<UserEntity> list = userEntityService.findAll();
        ModelAndView modelAndView = new ModelAndView("home","user",list);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam Optional<String> key_search){
        if(!key_search.isPresent()){
            return new ModelAndView("home", "user", userEntityService.findAll());
        }else {
            return new ModelAndView("home", "user", userEntityService.findByName(key_search.get()));
        }
    }

    @GetMapping("/register")
    public ModelAndView showFromRegister(@Validated @ModelAttribute("user") UserEntity userEntity, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("register","user",new UserEntity());
            return modelAndView;
        }else {

            ModelAndView modelAndView = new ModelAndView("register", "user", new UserEntity());
            return modelAndView;
        }
    }

    @PostMapping("/register")
    public  ModelAndView Register(@ModelAttribute("user") UserEntity userEntity){
        userEntityService.save(userEntity);
        ModelAndView modelAndView = new ModelAndView("register","user",new UserEntity());
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public  String delete(@PathVariable Long id){
        UserEntity userEntity = userEntityService.findUserById(id);
        userEntityService.deleteById(userEntity.getId());

        return "redirect:/home";
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showFromEdit(@PathVariable Long id){
       UserEntity userEntity =  userEntityService.findUserById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("user",userEntity);
        return modelAndView;
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute("user")UserEntity userEntity){
        userEntityService.save(userEntity);
        return "redirect:/home";
    }

    @GetMapping("/addNote")
    public ModelAndView showFromAddNote(){
        Note note  = new Note();
        ModelAndView modelAndView = new ModelAndView("addNote","note",note);
        return modelAndView;
    }
    @PostMapping("/addNote")
    public ModelAndView addNote(@ModelAttribute("note") Note note, Principal principal){
        noteService.save(note, userRepository.findByUserName(principal.getName()));
        ModelAndView modelAndView = new ModelAndView("addNote","note",new Note());
        return modelAndView;
    }
    @GetMapping("/listNote")
    public  ModelAndView listNote(){
        List<Note> list = noteService.findAllNote();
        ModelAndView modelAndView = new ModelAndView("listNote","note",list);
        return modelAndView;
    }
    @GetMapping("/{id}/deleteNote")
    public  String deleteNote(@PathVariable Long id){
        Note note = noteService.findNoteById(id);
        noteService.deleteNoteById(note.getId());

        return "redirect:/listNote";
    }
    @GetMapping("/searchNote")
    public ModelAndView searchNote(@RequestParam Optional<String> key_search){
        if(!key_search.isPresent()){
            return new ModelAndView("listNote", "note", noteService.findAllNote());
        }else {
            return new ModelAndView("listNote", "note", noteService.findByTile(key_search.get()));
        }
    }
    @GetMapping("/{id}/editNote")
    public ModelAndView showFromEditNote(@PathVariable Long id){
        Note note = noteService.findNoteById(id);
        ModelAndView modelAndView = new ModelAndView("editNote");
        modelAndView.addObject("note" ,note);
        return  modelAndView;
    }

    @PostMapping("/editNote")
    public String update(@ModelAttribute("note")Note note,Principal principal){
        noteService.save(note, userRepository.findByUserName(principal.getName()));
        return "redirect:/listNote";
    }
}
