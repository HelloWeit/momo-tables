package cn.weit.tables.dto;

import lombok.Data;

import java.util.List;

/**
 * @author weitong
 */
@Data
public class DemoExtDto {
	private Long id;

	private List<MetaDto> metaDtos;
}
