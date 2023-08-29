package booking.travel.enterprise.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import booking.travel.entity.Account;
import booking.travel.entity.FromCity;
import booking.travel.entity.Region;
import booking.travel.entity.ToCity;
import booking.travel.entity.TourPost;
import booking.travel.entity.Tranport;
import booking.travel.entity.Type;
import booking.travel.service.FromCityService;
import booking.travel.service.RegionService;
import booking.travel.service.ToCityService;
import booking.travel.service.TourPostService;
import booking.travel.service.TranportService;
import booking.travel.service.TypeService;
import booking.travel.utils.FileService;

@Controller
@RequestMapping("/enterprise/")
public class TourEnterpriseController {

	@Autowired
	FileService fileService;

	@Autowired
	RegionService regionService;

	@Autowired
	TypeService typeService;

	@Autowired
	TranportService tranportService;

	@Autowired
	FromCityService fromCityService;

	@Autowired
	TourPostService tourService;

	@Autowired
	ToCityService toCityService;

	/// TẠO MỚI FORM
	@RequestMapping("tour_form")
	public String enterpriseTourForm(Model model) {
		TourPost tourPost = new TourPost();
		model.addAttribute("tour", tourPost);
		return "enterprise/tour/_themTour";
	}

	// ĐỔ LOẠI TOUR LÊN COMBOBOX
	@ModelAttribute("typeList")
	public List<Type> listType() {
		return typeService.findAll();
	}

	// ĐỔ PHƯƠNG TIỆN LÊN COMBOBOX
	@ModelAttribute("tranportList")
	public List<Tranport> listTranport() {
		return tranportService.findAll();
	}

	// ĐỔ ĐIỂM KHỞI HÀNH LÊN COMBOBOX
	@ModelAttribute("fromCityList")
	public List<FromCity> listFromCity() {
		return fromCityService.findAll();
	}

	/// ĐỔ VÙNG LÊN COMBOBOX
	@ModelAttribute("regionList")
	public List<Region> listRegion() {
		return regionService.findAll();
	}

	/// LẤY ĐIỂM ĐẾN THEO VÙNG
	@ResponseBody
	@RequestMapping("api/toCity/{regionId}")
	public List<ToCity> toCity(@PathVariable("regionId") String regionId) {
		return toCityService.findAllByRegionId(regionId);
	}

	/// ĐỔ CÁC TOUR THEO USERNAME NGƯỜI DÙNG ĐĂNG NHẬP
	@RequestMapping("tour_list")
	public String enterpriseTourList(Model model, HttpServletRequest request) {

		String username = request.getRemoteUser();
		List<TourPost> list = tourService.findAllByUsername(username);
		model.addAttribute("listTour", list);

		return "enterprise/tour/_baiDangTour";
	}

	/// HÀM LƯU TOUR
	@PostMapping("save_tourPost")
	public String saveTour(TourPost tour,
			@RequestParam("mainImage") MultipartFile primaryMultipartFile,
			@RequestParam("extraImage") MultipartFile[] extraMultipartFiles, HttpServletRequest request)
			throws IOException {
			
			
			if(tour.getToCity().getToCityId().isEmpty()) {
				return null;
			}
			tour.setToCity(new ToCity(tour.getToCity().getToCityId().substring(7)));
			
			TourPost toursave =  tourService.save(tour);
			
			String folder = "tour/" + toursave.getFolder();
			
			String fileName = fileService.saveFile(folder, primaryMultipartFile);
			if (fileName != null) {
				toursave.setPrimaryImage(fileName);
			}

			int count = 0;
			for (MultipartFile extraMultipart : extraMultipartFiles) {
				String extraImageName = fileService.saveFile(folder, extraMultipart);
				if (extraImageName != null) {
					if (count == 0)
						toursave.setExtraImage1(extraImageName);
					if (count == 1)
						toursave.setExtraImage2(extraImageName);
					if (count == 2)
						toursave.setExtraImage3(extraImageName);
					if (count == 3)
						toursave.setExtraImage4(extraImageName);
					if (count == 4)
						toursave.setExtraImage5(extraImageName);
					if (count == 5)
						toursave.setExtraImage6(extraImageName);
				}
				count++;
			}

			toursave.setAccount(new Account(request.getRemoteUser()));

			tourService.save(toursave);

			return "redirect:/enterprise/tour_list";
	}

	/// CHỈNH SỬA TOUR
	@GetMapping("tour_edit/{tourId}")
	public String edit(Model model, @PathVariable("tourId") String id) {

		TourPost tourPost = tourService.findById(id);
		model.addAttribute("tour", tourPost);

		return "enterprise/tour/_themTour";
	}

	// XÓA TOUR
	@GetMapping("tour_delete/{tourId}")
	public String deleteTour(Model model, @PathVariable("tourId") String id) {

		TourPost tourPost = tourService.findById(id);

		return confirmDeleteTour(tourPost);
	}
	
	@PostMapping("confirm_tour_delete")
	public String confirmDeleteTour(TourPost tourPost) {
		
		tourPost.setIsDeleted(true);
		tourService.saveIsDeleted(tourPost);
		
		return "redirect:/enterprise/tour_list";
	}

	///// HÀM TÌM KIẾM THEO USERNAME
	@GetMapping("search_tour_post")
	public String searchPost(Model model, HttpServletRequest request, @RequestParam("keyword") String keyword) {

		String username = request.getRemoteUser();
		List<TourPost> list = tourService.searchTourByUsername(username, keyword);
		model.addAttribute("listTour", list);

		return "enterprise/tour/_baiDangTour";
	}

	@RequestMapping("/enterprise/tour_detail")
	public String enterpriseTourDetail() {
		return "enterprise/order/_detailDonTour";
	}
}
