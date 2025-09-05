import Swiper from "swiper";
import { Navigation, Pagination } from "swiper/modules";
import JustValidate from 'just-validate';

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "/src/sass/style.scss";

const burger = document.querySelector(".burger"),
	close = document.querySelector(".header__menu-close"),
	menu = document.querySelector(".header__menu");

burger.addEventListener("click", () => {
	menu.classList.add("header__menu_active");
	document.body.style.overflow = "hidden";
});

close.addEventListener("click", () => {
	menu.classList.remove("header__menu_active");
	document.body.style.overflow = "";
});

try {
	new Swiper(".works__slider", {
		slidesPerView: 1,
		loop: true,
		pagination: {
			el: ".swiper-pagination",
			clickable: true,
		},
		navigation: {
			nextEl: ".icon-right-open",
			prevEl: ".icon-left-open",
		},
		breakpoints: {
			// when window width is >= 1200px
			1200: {
				slidesPerView: 3,
				spaceBetween: 5,
			},
			1920: {
				spaceBetween: 35,
				slidesPerView: 3,
			},
		},
		modules: [Navigation, Pagination],
	});
} catch (e) { }

try {
	const tabs = document.querySelectorAll(".catalog__tab");
	const contents = document.querySelectorAll(".catalog__content-item");

	tabs.forEach((tab, index) => {
		tab.addEventListener("click", () => {
			// Удаляем активный класс у всех табов и контента
			tabs.forEach((t) => t.classList.remove("catalog__tab_active"));
			contents.forEach((c) => (c.style.display = "none"));

			// Добавляем активный класс к нажатому табу и показываем соответствующий контент
			tab.classList.add("catalog__tab_active");
			contents[index].style.display = "grid";
		});
	});

	// Показываем первый контент при загрузке
	contents.forEach((c, i) => (c.style.display = i === 0 ? "grid " : "none"));
} catch (e) { }

try {
	const validator = new JustValidate('.touch__form');

	validator
		.addField('#name', [
			{
				rule: 'required',
			},
			{
				rule: 'minLength',
				value: 2,
			},
		])
		.addField('#email', [
			{
				rule: 'required',
			},
			{
				rule: 'email',
			},
		])
		.addField('#question', [
			{
				rule: 'required',
			},
			{
				rule: 'minLength',
				value: 10,
			},
		], {
			errorsContainer: document.querySelector('#question').parentElement.querySelector('.error-message')
		})
		.addField('#checkbox', [
			{
				rule: 'required',
			},

		], {
			errorsContainer: document.querySelector('#checkbox').parentElement.parentElement.querySelector('.checkbox-error-message'),
		})
}
catch (e) { }



try {
	const valid = new JustValidate('.newsletter__form');
	valid
		.addField('#email', [
			{
				rule: 'required',
			},
			{
				rule: 'email',
			},
		])
		.addField('#checkbox', [
			{
				rule: 'required',
			},

		], {
			errorsContainer: document.querySelector('#checkbox').parentElement.parentElement.querySelector('.checkbox-error-message'),
		})
}
catch (e) {

}
