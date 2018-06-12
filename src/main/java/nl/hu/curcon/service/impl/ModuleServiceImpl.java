package nl.hu.curcon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.hu.curcon.dao.ModuleDao;
import nl.hu.curcon.domain.Function;
import nl.hu.curcon.domain.Module;
import nl.hu.curcon.dto.FunctionDto;
import nl.hu.curcon.dto.ModuleDto;
import nl.hu.curcon.dtomapper.Domain2DtoMapper;
import nl.hu.curcon.service.ModuleService;

@Service
public class ModuleServiceImpl extends GenericService implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;

	@Override
	public ModuleDto find(int id) {
		return Domain2DtoMapper.map(moduleDao.find(id));
	}

	@Transactional
	@Override
	public List<FunctionDto> findFunctionsByModuleId(int id) {
	    Module module = moduleDao.find(id);
		if (module == null)
			return null;
		List<FunctionDto> functions = new ArrayList<>();
		for (Function f : module.getFunctions()) {
			functions.add(Domain2DtoMapper.map(f));
		}
		return functions;
	}
	@Override
	public List<ModuleDto> findAll() {
		List<ModuleDto> dtos = new ArrayList<ModuleDto>();
		List<Module> modules = moduleDao.findAll();
		for (Module m : modules) {
			dtos.add(Domain2DtoMapper.map(m));
		}
		return dtos;
	}
}