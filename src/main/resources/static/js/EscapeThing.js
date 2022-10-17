class EscapeThing{
	static htmlEscape(text) {
		let result = String(text);
		result = result.replaceAll(/&/g, "&amp;");
		result = result.replaceAll(/</g, "&lt;");
		result = result.replaceAll(/>/g, "&gt;");
		return result;
	}
}
