package com.ces2.clase9.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ces2.clase9.model.*;
import com.ces2.clase9.model.form.ExplorerWeaponForm;
import com.ces2.clase9.repository.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ces2.clase9.model.Explorer;

@Controller
@RequestMapping("/weapon")
public class WeaponController {
	
	private final WeaponRepository weaponRepository;

	private final ExplorerRepository explorerRepository;

	private final MissionRepository missionRepository;

	private final EnhancementRepository enhancementRepository;
	private final ExplorerMissionRepository explorerMissionRepository;

	public WeaponController(WeaponRepository weaponRepository,
							ExplorerRepository explorerRepository,
							MissionRepository missionRepository,
							EnhancementRepository enhancementRepository,
							ExplorerMissionRepository explorerMissionRepository) {
		this.weaponRepository = weaponRepository;
		this.explorerRepository = explorerRepository;
		this.missionRepository = missionRepository;
		this.enhancementRepository = enhancementRepository;
		this.explorerMissionRepository = explorerMissionRepository;
	}

	@GetMapping
	public String getHomePage(Model model) {
		model.addAttribute("newExplorer", new Explorer());
		return "home";
	}

	@GetMapping("/registerExplorerWeapon")
	public String showForm(Model model) {
		model.addAttribute("explorerWeaponForm", new ExplorerWeaponForm());
		return "addExplorerWeapon";
	}

	@PostMapping("/saveExplorerWeapon")
	public String saveExplorerWeapon(ExplorerWeaponForm explorerWeaponForm) {
		Explorer explorer = explorerWeaponForm.getExplorer();
		Weapon weapon = explorerWeaponForm.getWeapon();

		Explorer savedExplorer = explorerRepository.save(explorer);

		weapon.setExplorerId(AggregateReference.to(savedExplorer.getId()));

		weaponRepository.save(weapon);

		return "redirect:/weapon";
	}

	@GetMapping("/explorers")
	public String listExplorers(Model model) {
		// Obtener todos los exploradores de la base de datos
		Iterable<Explorer> explorers = explorerRepository.findAll();
		model.addAttribute("explorers", explorers);
		return "explorersList"; // Nombre de la plantilla a usar
	}

	@GetMapping("/explorer/{id}/weapon")
	public String getWeaponByExplorerId(@PathVariable Integer id, Model model) {
		// Buscar el arma asociada al explorador usando el ID
		Optional<Weapon> weapon = weaponRepository.findByExplorerId(id);

		if (weapon.isPresent()) {
			model.addAttribute("weapon", weapon.get());
			return "weaponDetails"; // Nombre de la plantilla para mostrar los detalles
		} else {
			model.addAttribute("errorMessage", "No se encontró un arma para este explorador.");
			return "error"; // Plantilla para manejar errores
		}
	}

	@PostMapping("/explorer/{id}/weapon/enhancement")
	public String addEnhancementToWeapon(@PathVariable Integer id, @ModelAttribute Enhancement enhancement, Model model) {
		Optional<Weapon> weaponOptional = weaponRepository.findByExplorerId(id);

		if (weaponOptional.isPresent()) {
			Weapon weapon = weaponOptional.get();

			// Verificar que el enhancement no sea nulo
			if (enhancement != null) {
				// Asignar el ID de la weapon al enhancement
				enhancement.setWeaponId(weapon.getId());

				// Agregar el enhancement a la weapon
				weapon.getEnhancements().add(enhancement);

				// Guardar los cambios en la base de datos
				weaponRepository.save(weapon);

				model.addAttribute("weapon", weapon);
				return "weaponDetails"; // Regresar a la vista de detalles de la weapon
			} else {
				model.addAttribute("errorMessage", "No se pudo agregar el enhancement.");
				return "error"; // Manejar el error si no se pudo agregar el enhancement
			}
		} else {
			model.addAttribute("errorMessage", "No se encontró el arma para este explorador.");
			return "error"; // Manejar el error si no se encuentra el arma
		}
	}

	@GetMapping("/{id}/enhancements")
	public String viewEnhancements(@PathVariable Integer id, Model model) {
		Optional<Weapon> weaponOptional = weaponRepository.findById(id);

		if (weaponOptional.isPresent()) {
			Weapon weapon = weaponOptional.get();

			Set<Enhancement> enhancements = weapon.getEnhancements();

			model.addAttribute("weapon", weapon);
			model.addAttribute("enhancements", enhancements);

			return "enhancementsList";
		} else {
			model.addAttribute("errorMessage", "No se encontró el arma.");
			return "error";
		}
	}

	@GetMapping("/mission/create")
	public String showCreateMissionForm(Model model) {
		model.addAttribute("mission", new Mission());
		model.addAttribute("statuses", Estado.values());
		model.addAttribute("types", Tipo.values());
		return "createMission";
	}

	@PostMapping("/mission/save")
	public String saveMission(@ModelAttribute Mission mission, Model model) {
		missionRepository.save(mission); // Guarda la misión en la base de datos
		model.addAttribute("successMessage", "Misión creada con éxito!");
		return "redirect:/weapon";
	}

	@GetMapping("/missions")
	public String listMissions(Model model) {
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("missions", missions);
		return "missionsList"; // Vista para listar todas las misiones
	}

	@GetMapping("/assign")
	public String showAssignForm(Model model) {
		// Obtener todos los exploradores y misiones
		Iterable<Explorer> explorers = explorerRepository.findAll();
		Iterable<Mission> missions = missionRepository.findAll();

		// Añadir los datos al modelo
		model.addAttribute("explorers", explorers);
		model.addAttribute("missions", missions);
		model.addAttribute("explorerMission", new ExplorerMission());

		return "assignExplorerMission";
	}

	@PostMapping("/assign")
	public String assignMissionToExplorer(@ModelAttribute ExplorerMission explorerMission, Model model) {
		explorerMission.setAssignedDate(LocalDateTime.now());

		explorerMissionRepository.save(explorerMission);

		// Redirigir al usuario con un mensaje de éxito
		model.addAttribute("successMessage", "Misión asignada con éxito.");
		return "redirect:/weapon";
	}

	@GetMapping("/mission/{id}/explorers")
	public String getExplorersByMissionId(@PathVariable Integer id, Model model) {
		Optional<Mission> missionOptional = missionRepository.findById(id);

		if (missionOptional.isPresent()) {
			Mission mission = missionOptional.get();

			Iterable<ExplorerMission> explorerMissions = explorerMissionRepository.findByMissionId(mission.getId());

			List<Explorer> explorers = new ArrayList<>();
			for (ExplorerMission explorerMission : explorerMissions) {
				explorerRepository.findById(explorerMission.getExplorerId()).ifPresent(explorers::add);
			}

			model.addAttribute("mission", mission);
			model.addAttribute("explorers", explorers);
			return "missionExplorersList"; // Vista para mostrar los exploradores
		} else {
			model.addAttribute("errorMessage", "No se encontró la misión.");
			return "error"; // Vista de error
		}
	}

	@GetMapping("/explorer/{id}/missions")
	public String getMissionsByExplorerId(@PathVariable Integer id, Model model) {
		// Buscar al explorador
		Optional<Explorer> explorerOptional = explorerRepository.findById(id);

		if (explorerOptional.isPresent()) {
			Explorer explorer = explorerOptional.get();

			// Consultar las misiones asignadas al explorador
			List<Mission> missions = missionRepository.findMissionsByExplorerId(explorer.getId());

			model.addAttribute("explorer", explorer);
			model.addAttribute("missions", missions);

			return "explorerMissionsList"; // Vista para mostrar las misiones
		} else {
			model.addAttribute("errorMessage", "No se encontró el explorador.");
			return "error"; // Vista de error
		}
	}
}
