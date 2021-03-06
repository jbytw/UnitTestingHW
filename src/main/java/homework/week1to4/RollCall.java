package homework.week1to4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class RollCall {
	private IRandom random = null;

	public RollCall() {
	}

	public RollCall(IRandom random) {
		setRandom(random);
	}

	public void setRandom(IRandom random) {
		this.random = random;
	}

	public IRandom getRandom() {
		return this.random;
	}

	public static void main(String[] args) {
		RollCall rollCall = new RollCall(new RandomWrapper());
		List<Student> listStudent = rollCall
				.loadCsvToStudent("src/main/resources/Week1HW_sample.csv");

		for (Student student : listStudent) {
			boolean isValid = rollCall.checkStudentFormat(student);

			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]" + " isValid=" + isValid);
		}
		System.out.println("Total: " + listStudent.size() + " people");
		System.out.println("isUniqueStudentList: "
				+ rollCall.checkUniqueStudentList(listStudent));

		System.out.println(StringUtils.repeat("-", 60));

		List<Student> listStudentWeek7 = rollCall.getWeek7List(listStudent);
		for (Student student : listStudentWeek7) {
			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]");
		}
		System.out.println("Week7: " + listStudentWeek7.size() + " people");

		System.out.println(StringUtils.repeat("-", 60));

		List<Student> listStudentWeek8 = rollCall.getWeek8List(listStudent);
		for (Student student : listStudentWeek8) {
			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]");
		}
		System.out.println("Week8: " + listStudentWeek8.size() + " people");

		System.out.println(StringUtils.repeat("-", 60));

		List<Student> listStudentWeek7AndWeek8 = rollCall
				.getWeek7AndWeek8List(listStudent);
		for (Student student : listStudentWeek7AndWeek8) {
			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]");
		}
		System.out.println("Week7 & Week8: " + listStudentWeek7AndWeek8.size()
				+ " people");

		System.out.println(StringUtils.repeat("-", 60));

		List<Student> listStudentWeek7OrWeek8 = rollCall
				.getWeek7OrWeek8List(listStudent);
		for (Student student : listStudentWeek7OrWeek8) {
			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]");
		}
		System.out.println("Week7 or Week8: " + listStudentWeek7OrWeek8.size()
				+ " people");

		System.out.println(StringUtils.repeat("-", 60));

		// Random random = new Random(System.currentTimeMillis());
		List<Student> listSequenceStudent = rollCall
				.getRandomPresentationSequence(listStudent);
		for (Student student : listSequenceStudent) {
			System.out.println("Student [id=" + student.getId() + ", isWeek7="
					+ student.getIsWeek7() + ", isWeek8="
					+ student.getIsWeek8() + "]");
		}
		System.out.println("Total Presenter: " + listSequenceStudent.size()
				+ " people");
	}

	// http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	public List<Student> loadCsvToStudent(String filePath) {
		List<Student> listStudent = new ArrayList<Student>();

		BufferedReader bufferedReader = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			while ((line = bufferedReader.readLine()) != null) {
				// use comma as separator
				String[] studentInfo = line.split(cvsSplitBy);
				Student student = new Student(Integer.parseInt(studentInfo[0]),
						Integer.parseInt(studentInfo[1]),
						Integer.parseInt(studentInfo[2]));

				listStudent.add(student);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return listStudent;
	}

	public boolean checkStudentFormat(Student student) {
		if (student.getId() <= 0) {
			return false;
		}

		if (student.getIsWeek7() > 1 || student.getIsWeek7() < 0) {
			return false;
		}

		if (student.getIsWeek8() > 1 || student.getIsWeek8() < 0) {
			return false;
		}

		return true;
	}

	public boolean checkUniqueStudentList(List<Student> listStudent) {
		Set<Student> set = new HashSet<Student>(listStudent);

		if (set.size() < listStudent.size()) {
			return false;
		}

		return true;
	}

	public List<Student> getWeek7List(List<Student> listStudent) {
		List<Student> listStudentWeek7 = new ArrayList<Student>();

		for (Student student : listStudent) {
			if (student.getIsWeek7() == 1) {
				listStudentWeek7.add(student);
			}
		}

		return listStudentWeek7;
	}

	public List<Student> getWeek8List(List<Student> listStudent) {
		List<Student> listStudentWeek8 = new ArrayList<Student>();

		for (Student student : listStudent) {
			if (student.getIsWeek8() == 1) {
				listStudentWeek8.add(student);
			}
		}

		return listStudentWeek8;
	}

	public List<Student> getWeek7AndWeek8List(List<Student> listStudent) {
		List<Student> listStudentWeek7AndWeek8 = new ArrayList<Student>();

		for (Student student : listStudent) {
			if (student.getIsWeek7() == 1 && student.getIsWeek8() == 1) {
				listStudentWeek7AndWeek8.add(student);
			}
		}

		return listStudentWeek7AndWeek8;
	}

	public List<Student> getWeek7OrWeek8List(List<Student> listStudent) {
		List<Student> listStudentWeek7OrWeek8 = new ArrayList<Student>();

		for (Student student : listStudent) {
			if (student.getIsWeek7() == 1 || student.getIsWeek8() == 1) {
				listStudentWeek7OrWeek8.add(student);
			}
		}

		return listStudentWeek7OrWeek8;
	}

	public List<Student> getRandomPresentationSequence(List<Student> listStudent) {
		// get students showing up on week 7 or week 8
		List<Student> listSequenceStudent = getWeek7OrWeek8List(listStudent);
		int studentCount = listSequenceStudent.size();

		List<Student> listSequenceStudentWeek7 = new ArrayList<Student>();
		List<Student> listSequenceStudentWeek8 = new ArrayList<Student>();

		for (int i = 0; i < studentCount; i++) {
			int next = random.nextInt(listSequenceStudent.size());
			if (listSequenceStudent.get(next).getIsWeek7() == 1
					&& listSequenceStudent.get(next).getIsWeek8() != 1) {
				listSequenceStudentWeek7.add(listSequenceStudent.get(next));

			} else if (listSequenceStudent.get(next).getIsWeek7() != 1
					&& listSequenceStudent.get(next).getIsWeek8() == 1) {
				listSequenceStudentWeek8.add(listSequenceStudent.get(next));
			} else {
				if ((next % 2) == 0) {
					listSequenceStudentWeek7.add(listSequenceStudent.get(next));
				} else {
					listSequenceStudentWeek8.add(listSequenceStudent.get(next));
				}
			}

			listSequenceStudent.remove(next);
		}

		listSequenceStudent.addAll(listSequenceStudentWeek7);
		listSequenceStudent.addAll(listSequenceStudentWeek8);

		return listSequenceStudent;
	}

}