package booking.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FavoriteController {

	@RequestMapping("/list/favorite")
	public String list() {
		return "favorite/_listMyFavorite";
	}
}
