package com.example.zuccecho.Services.implement;
import com.example.zuccecho.DTO.LectureDTO;
import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Repository.LectureRepository;
import com.example.zuccecho.Services.LectureServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class LectureServicesImplement implements LectureServices {
    @Autowired
    private LectureRepository lr;

    @Cacheable(key = "#p0.getId()",value = "LectureID#2")
    public Lecture creatLecture(LectureDTO lectureDTO){
        Lecture lecture = new Lecture();
        //错误校验后用entity类存入数据库
        BeanUtils.copyProperties(lectureDTO, lecture);
        lr.save(lecture);
        return lecture;
    }

    public void closeLecture(Long lectureID){
        Lecture lecture = lr.findById(lectureID).get();
        lecture.setOpen(false);
        lr.save(lecture);
    }

    @CacheEvict(key = "#p0",value = "LectureID")
    public void deleteLecture(Long lectureID){
        lr.deleteById(lectureID);
    }

    @CachePut(key = "#p0.getId()",value = "LectureID#2")
    public boolean updateLecture(LectureDTO lectureDTO){
        try{
            Lecture lecture = new Lecture();
            //错误校验后用entity类存入数据库
            BeanUtils.copyProperties(lectureDTO, lecture);
            lr.save(lecture);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Cacheable(key = "#p0",value = "LectureID#2")
    public Lecture findLecture(Long lectureID) {
        return lr.findById(lectureID).get();
    }

    //填充测试不设置response
    public void fillLecture(){
        Lecture a = new Lecture();
        a.setName("数据结果");
        a.setArea("理4");
        a.setId(12344l);
        lr.save(a);
    }

}
