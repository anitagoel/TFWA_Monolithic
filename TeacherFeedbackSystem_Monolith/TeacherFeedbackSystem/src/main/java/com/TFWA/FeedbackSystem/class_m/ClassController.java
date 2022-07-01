package com.ishitwa.FeedbackSystem.class_m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassById(@PathVariable long id){
        long classId= id;
        try{
            Class c=classService.getClassFromId(classId);
            return new ResponseEntity<>(
                    c,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewClass(@RequestBody Class c){
        try{
            classService.saveClass(c);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    "Error in saving class",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @PostMapping("/changeDepartment")
//    public ResponseEntity<?> changeDepartment(@RequestBody Id2 id){
//        try{
//            long classId=id.getId1();
//            long departmentId=id.getId2();
//            Class c=classService.updateDepartment(classId,departmentId);
//            return new ResponseEntity<>(c,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(
//                    e.getMessage(),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }

    @GetMapping("/getAllClasses")
    public ResponseEntity<?> getAllClasses(){
        try{
            List<Class> classes=classService.getAllClasses();
            return new ResponseEntity<>(classes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getClassesByDepartment/{id}")
    public ResponseEntity<?> getClasses(@PathVariable long id){
        try{
            List<Class> classes=classService.getClassesByDepartment(id);
            return new ResponseEntity<>(
                    classes,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
