package com.alvis.exam.controller.admin;


import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Capter;
import com.alvis.exam.domain.Subject;
import com.alvis.exam.service.CapterService;
import com.alvis.exam.service.SubjectService;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.education.CapterEditRequestVM;
import com.alvis.exam.viewmodel.admin.education.CapterPageRequestVM;
import com.alvis.exam.viewmodel.admin.education.CapterResponseVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("AdminEducationController")
@RequestMapping(value = "/api/admin/education")
@AllArgsConstructor
public class EducationController extends BaseApiController {

    private final CapterService capterService;

    @RequestMapping(value = "/capter/list", method = RequestMethod.POST)
    public RestResponse<List<Capter>> list() {
        List<Capter> capters = capterService.allCapter();
        return RestResponse.ok(capters);
    }

    @RequestMapping(value = "/capter/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<CapterResponseVM>> pageList(@RequestBody CapterPageRequestVM model) {
        PageInfo<Capter> pageInfo = capterService.page(model);
        if (model.getChapterName() != null) {
            model.setChapterName("%" + model.getChapterName() + "%");
        }
        PageInfo<CapterResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, CapterResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/capter/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid CapterEditRequestVM model) {
        Capter capter = modelMapper.map(model, Capter.class);
        if (model.getId() == null) {
            capter.setDeleted(false);
            capterService.insertByFilter(capter);
        } else {
            capterService.updateByIdFilter(capter);
        }
        return RestResponse.ok();
    }

    @RequestMapping(value = "/capter/select/{id}", method = RequestMethod.POST)
    public RestResponse<CapterEditRequestVM> select(@PathVariable Integer id) {
        Capter capter = capterService.selectById(id);
        CapterEditRequestVM vm = modelMapper.map(capter, CapterEditRequestVM.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/capter/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Capter capter = capterService.selectById(id);
        capter.setDeleted(true);
        capterService.updateByIdFilter(capter);
        return RestResponse.ok();
    }
}
