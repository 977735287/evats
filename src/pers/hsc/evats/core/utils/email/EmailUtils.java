package pers.hsc.evats.core.utils.email;

import java.text.MessageFormat;

/**
 * @author hsc
 *
 *         Apr 19, 2018
 */
public class EmailUtils {

	public static int c = 0;

	public static String analysisTemplate(String template, String... datas) {
		int jude = getParamNum(template);
		if (datas.length < jude) {
			jude = datas.length;
		}
		switch (jude) {
		case 0:
			return template;
		case 1:
			return MessageFormat.format(template, datas[0]);
		case 2:
			return MessageFormat.format(template, datas[0], datas[1]);
		case 3:
			return MessageFormat.format(template, datas[0], datas[1], datas[2]);
		case 4:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3]);
		case 5:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4]);
		case 6:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5]);
		case 7:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6]);
		case 8:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6],
					datas[7]);
		case 9:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6],
					datas[7], datas[8]);
		case 10:
			return MessageFormat.format(template, datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6],
					datas[7], datas[8], datas[9]);
		default:
			return template;
		}
	}

	public static int getParamNum(String template) {
		String param = "{" + c + "}";
		if (template.contains(param)) {
			c++;
			return getParamNum(template);
		}
		return c;
	}
}
