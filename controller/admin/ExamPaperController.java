package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.base.SystemCode;
import com.alvis.exam.domain.ExamPaper;
import com.alvis.exam.domain.User;
import com.alvis.exam.repository.UserMapper;
import com.alvis.exam.service.ExamPaperService;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.utility.ErrorUtil;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.exam.*;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("AdminExamPaperController")
@RequestMapping(value = "/api/admin/exam/paper")
@AllArgsConstructor
public class ExamPaperController extends BaseApiController {

    @Autowired
    UserMapper userMapper;
    private final ExamPaperService examPaperService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> pageList(@RequestBody ExamPaperPageRequestVM model) {
        if(model.getName()!=null){
            model.setName("%" + model.getName() + "%");
        }
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            User user = userMapper.getUserById(e.getCreateUser());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            if (user != null) {
                vm.setCreateUserName(user.getRealName());
            }
            return vm;
        });
        return RestResponse.ok(page);
    }
    @RequestMapping(value = "/generate/report", method = RequestMethod.POST)
    public RestResponse<Map<String, Object>> generateReport(@RequestBody ExamPaperPageGenerateRequestVM model) {
        if(model.getPaperIds()==null || model.getPaperIds().length()==0){
            return new RestResponse<>(SystemCode.ParameterValidError.getCode(), "id不能为空");
        }
        List<Integer> paperIds = new ArrayList<>();
        String[] paperIdSplit=model.getPaperIds().split(",");
        for(String paperId: paperIdSplit){
            if(paperId!=null&&paperId.length()>0){
                paperIds.add(Integer.valueOf(paperId));
            }
        }
        Map<String, Object> stringObjectMap = examPaperService.generateReport(paperIds);
        return RestResponse.ok(stringObjectMap);
    }
    @RequestMapping(value = "/autopaper", method = RequestMethod.POST)
    public RestResponse autopaper(@RequestBody ExamPaperAutoPaperRequestVM model) {
        if(model.getCapterId()==null|| model.getCapterId().size()==0){
            return new RestResponse<>(SystemCode.ParameterValidError.getCode(), "未选择章节");
        }
        examPaperService.autopaper(model,getCurrentUser());
        return RestResponse.ok();
    }


    @RequestMapping(value = "/taskExamPage", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponseVM>> taskExamPageList(@RequestBody ExamPaperPageRequestVM model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.taskExamPage(model);
        PageInfo<ExamResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponseVM vm = modelMapper.map(e, ExamResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> edit(@RequestBody @Valid ExamPaperEditRequestVM model) {
        ExamPaper examPaper = examPaperService.savePaperFromVM(model, getCurrentUser());
        ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
        return RestResponse.ok(newVM);
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id) {
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        ExamPaper examPaper = examPaperService.selectById(id);
        examPaper.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper);
        return RestResponse.ok();
    }
}
