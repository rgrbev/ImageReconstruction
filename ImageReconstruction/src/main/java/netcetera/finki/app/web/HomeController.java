package netcetera.finki.app.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.Workspace;
import netcetera.finki.app.model.WorkspaceJob;
import netcetera.finki.app.service.ImageService;
import netcetera.finki.app.service.KeypointsService;
import netcetera.finki.app.service.WorkspaceJobService;
import netcetera.finki.app.service.WorkspaceService;

@Controller
public class HomeController {
	@Autowired
	WorkspaceService workspaceService;
	@Autowired
	ImageService imageService;
	@Autowired
	WorkspaceJobService workspaceJobService;
	@Autowired
	KeypointsService keypointService;

	ArrayList<Image> images = new ArrayList<Image>();

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public ModelAndView getFirst() {
		ModelAndView m = new ModelAndView("first");
		ArrayList<Workspace> workspaces = (ArrayList<Workspace>) workspaceService.getAllWorkspaces();
		m.addObject("workspaces", workspaces);
		return m;
	}

	@RequestMapping(value = "/newWorkspace", method = RequestMethod.GET)
	public ModelAndView newWorkspace() {
		ModelAndView m = new ModelAndView("newWorkspace");
		ArrayList<Workspace> workspaces = (ArrayList<Workspace>) workspaceService.getAllWorkspaces();
		m.addObject("workspaces", workspaces);
		return m;
	}

	@RequestMapping(value = "/createWorkspace", method = RequestMethod.POST)
	public ModelAndView createWorkspace(@RequestParam("name") String name,
			@RequestParam("description") String description) {

		workspaceService.addWorkspace(name, description);
		ModelAndView m = new ModelAndView("first");
		ArrayList<Workspace> workspaces = (ArrayList<Workspace>) workspaceService.getAllWorkspaces();
		m.addObject("workspaces", workspaces);
		return m;
	}

	@RequestMapping(value = "/workspace/{id}", method = RequestMethod.GET)
	public ModelAndView viewWorkspace(@PathVariable long id) {

		ArrayList<Image> images = (ArrayList<Image>) imageService.getAllImagesForWorkspace(id);
		ModelAndView m = new ModelAndView("workspaceDetails");
		ArrayList<Workspace> workspaces = (ArrayList<Workspace>) workspaceService.getAllWorkspaces();
		m.addObject("workspaces", workspaces);
		m.addObject("workspace", workspaceService.getWorkspaceById(id));
		m.addObject("workspaceName", workspaceService.getWorkspaceById(id).getName());
		m.addObject("images", images);

		return m;
	}

	@RequestMapping(value = "/workspace/uploadImage", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("idWorkspace") String idWorkspace,
			@RequestParam("files[]") MultipartFile[] files) {

		long id = Long.parseLong(idWorkspace);
		for (MultipartFile f : files) {

			String name = f.getOriginalFilename();
			imageService.addImage(id, f, name);
		}
		return "redirect:/workspace/" + idWorkspace;
	}

	@RequestMapping(value = "/workspace/editWorkspace", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("idWorkspace") String idWorkspace, @RequestParam("name") String name,
			@RequestParam("description") String description) {
		long id = Long.parseLong(idWorkspace);
		workspaceService.editWorkspace(id, name, description);
		return "redirect:/workspace/" + idWorkspace;
	}

	@RequestMapping(value = "/workspace/deleteImage/{id}", method = RequestMethod.GET)
	public String deleteImage(@PathVariable long id, @RequestParam("idWorkspace") String idWorkspace) {

		imageService.deleteImage(id);
		return "redirect:/workspace/" + idWorkspace;
	}

	@RequestMapping(value = "/workspace/deleteWorkspace/{id}", method = RequestMethod.GET)
	public String deleteWorkspace(@PathVariable long id) {

		workspaceService.deleteWorkspace(id);
		return "redirect:/first";
	}

	@RequestMapping(value = "/workspace/configureProcessing/{id}", method = RequestMethod.GET)
	public ModelAndView configureProcessing(@PathVariable long id) {

		ModelAndView m = new ModelAndView("workspaceJobs");
		ArrayList<Workspace> workspaces = (ArrayList<Workspace>) workspaceService.getAllWorkspaces();
		ArrayList<WorkspaceJob> workspaceJobs = (ArrayList<WorkspaceJob>) workspaceJobService
				.getAllWorkspaceJobsForWorkspace(id);
		m.addObject("workspaceJobs", workspaceJobs);
		m.addObject("workspaces", workspaces);
		m.addObject("workspace", workspaceService.getWorkspaceById(id));
		return m;
	}

	@RequestMapping(value = "/workspace/configureProcessing/createWorkspaceJob", method = RequestMethod.POST)
	public String createWorkspaceJob(@RequestParam("name") String name, @RequestParam("detector") String detector,
			@RequestParam(value = "ds_spacing", required = false) String ds_spacing,
			@RequestParam(value = "ds_spacingValue", required = false) String ds_spacingValue,
			@RequestParam(value = "ds_scales", required = false) String ds_scales,
			@RequestParam(value = "ds_scalesValue", required = false) String ds_scalesValue,
			@RequestParam(value = "harrisThreshold", required = false) String harrisThreshold,
			@RequestParam(value = "harrisThresholdValue", required = false) String harrisThresholdValue,
			@RequestParam(value = "harrisK", required = false) String harrisK,
			@RequestParam(value = "harrisKValue", required = false) String harrisKValue,
			@RequestParam(value = "laplaceThreshold", required = false) String laplaceThreshold,
			@RequestParam(value = "laplaceThresholdValue", required = false) String laplaceThresholdValue,
			@RequestParam("descriptor") String descriptor, @RequestParam("idWorkspace") String idWorkspace) {
		long id = Long.parseLong(idWorkspace);
		// double addOptVal = Double.parseDouble(additionalOptDetectorValue);
		/*
		 * System.out.println( name + " " + id + " " + detector + " " +
		 * ds_scales + " " + ds_scalesValue + " " + ds_spacing+ " " +
		 * ds_spacingValue + " " + descriptor + " "+ harrisThreshold);
		 */

		// workspaceJobService.addWorkspaceJob(name, id, detector,
		// additionalOptions, descriptor, addOptVal);

		String additionalDescriptorOptions = "";
		if (ds_scales == null) {
			ds_scales = "";
			ds_scalesValue = "";
		}
		if (ds_spacing == null) {
			ds_spacing = "";
			ds_spacingValue = "";
		}
		if (harrisK == null) {
			harrisK = "";
			harrisKValue = "";
		}
		if (harrisThreshold == null) {
			harrisThreshold = "";
			harrisThresholdValue = "";
		}
		if (laplaceThreshold == null) {
			laplaceThreshold = "";
			laplaceThresholdValue = "";
		}

		additionalDescriptorOptions = ds_scales + " " + ds_scalesValue + " " + ds_spacing + " " + ds_spacingValue + " "
				+ harrisK + " " + harrisKValue + " " + harrisThreshold + " " + harrisThresholdValue + " "
				+ laplaceThreshold + " " + laplaceThresholdValue;
		additionalDescriptorOptions = additionalDescriptorOptions.trim();
		if (additionalDescriptorOptions.length() == 0) {
			additionalDescriptorOptions = "NO";
		}
		workspaceJobService.addWorkspaceJob(name, id, detector, additionalDescriptorOptions, descriptor);
		return "redirect:/workspace/configureProcessing/" + id;
	}

	@RequestMapping(value = "/workspace/configureProcessing/deleteWorkspaceJob/{id}", method = RequestMethod.GET)
	public String deleteWorkspaceJob(@PathVariable long id, @RequestParam("idWorkspace") String idWorkspace) {

		workspaceJobService.deleteWorkspaceJob(id);
		long idW = Long.parseLong(idWorkspace);
		return "redirect:/workspace/configureProcessing/" + idW;
	}

	@RequestMapping(value = "/workspace/configureProcessing/startProcessing/{id}", method = RequestMethod.GET)
	public String startProcessing(@PathVariable long id, @RequestParam("idWorkspace") String idWorkspace)
			throws IOException {
		WorkspaceJob wj = workspaceJobService.getWorkspaceJob(id);
		String detector = wj.getDetector();
		String additionalOptions = wj.getAdditionalDetectorOptions();
		String descriptor = wj.getDescriptor();

		long idW = Long.parseLong(idWorkspace);
		ArrayList<Image> images = (ArrayList<Image>) imageService.getAllImagesForWorkspace(idW);

		String command = "";
		int k = 0;
		for (Image i : images) {
			k++;
			String imagePath = i.getNamePath();
			String[] tmp = imagePath.split("\\\\");
			String tmpImagePath = tmp[tmp.length - 2] + "\\" + tmp[tmp.length - 1];
			String pictureName = tmp[tmp.length - 1];
			String workspaceName = tmp[tmp.length - 2];
			File f = new File(Properties.resultsPath + workspaceName);
			if (!f.exists()) {
				f.mkdirs();
				System.err.println(f.getAbsolutePath());
			}
			command = Properties.colorDescriptorPath+" \""+Properties.firstPartInputDir
					+ tmpImagePath + "\" --detector " + detector + " " + additionalOptions + " --descriptor " + descriptor
					+ " --output \""+ Properties.resultsPath + workspaceName + "\\"
					+ pictureName + ".txt\"";
			System.out.println("**** " + k + "****" + command);
			Process process = Runtime.getRuntime().exec(command);
			while (process.isAlive()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			workspaceJobService.setDateFinished(wj.getId());
			String outputPath = Properties.resultsPath + workspaceName + "\\"
					+ pictureName + ".txt";
			keypointService.readAndSaveResults(outputPath, i.getId());
		}
		// workspaceJobService.setDateFinished(idW);
		return "redirect:/workspace/" + idW;
	}
}
