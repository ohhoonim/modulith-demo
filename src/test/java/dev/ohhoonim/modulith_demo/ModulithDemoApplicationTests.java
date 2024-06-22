package dev.ohhoonim.modulith_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ModulithDemoApplicationTests {

	@Test
	void contextLoads() {
		var modules = ApplicationModules.of(ModulithDemoApplication.class);
		for (var m : modules)
			System.out.println(m.getName() + ":" + m.getBasePackage());
		modules.verify();

		new Documenter(modules).writeIndividualModulesAsPlantUml().writeModulesAsPlantUml();
	}

}
