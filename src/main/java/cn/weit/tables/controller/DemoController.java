package cn.weit.tables.controller;


import cn.weit.tables.dto.DemoExtDto;
import cn.weit.tables.dto.RestResult;
import cn.weit.tables.service.FieldService;
import cn.weit.tables.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author weitong
 */
@RestController
@RequestMapping("/momo")
public class DemoController {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private MetaService metaService;

	@GetMapping("/table")
	public RestResult<?> test() {
		fieldService.updateExtInfo();
		return RestResult.success();


	}

	@GetMapping("/meta")
	public RestResult<?> cache() {
		metaService.parse();
		return RestResult.success();

	}

	@PostMapping("/value")
	public RestResult<?> add(@RequestBody DemoExtDto demoExtDto) {
		metaService.addValue(demoExtDto);
		return RestResult.success();
	}
}
