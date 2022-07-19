package exercise_mvc.service.impl;

import exercise_mvc.model.Student;
import exercise_mvc.model.Teacher;
import exercise_mvc.service.ITeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherService implements ITeacherService {
    private static List<Teacher> teacherList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    static {
        teacherList.add(new Teacher(1, "Đặng Chí Trung", "01/03/1990", 1, "Giảng viên lý thuyết"));
        teacherList.add(new Teacher(2, "Nguyễn Thanh Công", "29/12/1991", 1, "Giảng viên thực hành"));
        teacherList.add(new Teacher(3, "Trương Tấn Hải", "13/10/1998", 1, "Giảng viên thực hành"));
        teacherList.add(new Teacher(4, "Nguyễn Ngọc QUang", "06/09/1995", 1, "Giảng viên lý thuyết"));
    }

    @Override
    public void add() {
        Teacher teacher = infoTeacher();
        teacherList.add(teacher);
        System.out.println("Thêm mới thành công!");
    }

    @Override
    public void remove() {
        System.out.println("Mời bạn nhập id cần xóa: ");
        int idRemove = Integer.parseInt(scanner.nextLine());
        boolean isExist = false;

        for (Teacher teacher : teacherList) {
            if(teacher.getId() == idRemove) {
                System.out.println("Bạn có chắc muốn xóa hay không? \n" +
                        "1. Có \n" +
                        "2. Không");
                int chooseYesNo = Integer.parseInt(scanner.nextLine());

                if (chooseYesNo == 1) {
                    teacherList.remove(teacher);
                    System.out.println("Xóa thành công!");
                }
                isExist = true;
                break;
            }
        }
        if(!isExist) {
            System.out.println("Không tìm thấy!");
        }
    }

    @Override
    public void displayAll() {
        for (Teacher teacher : teacherList) {
            System.out.println(teacher);
        }
    }

    @Override
    public void findId() {
        System.out.println("Mời bạn nhập id cần tìm kiếm: ");
        int idFind = Integer.parseInt(scanner.nextLine());
        boolean isExist = false;

        for (Teacher teacher : teacherList) {
            if (teacher.getId() == idFind) {
                System.out.println(teacher);
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            System.out.println("Không tìm thấy!");
        }
    }

    @Override
    public void findName() {
        System.out.println("Mời bạn nhập tên cần tìm kiếm: ");
        String nameFind = scanner.nextLine();
        boolean isExist = false;

        for (Teacher teacher : teacherList) {
            if (teacher.getName().toLowerCase().contains(nameFind.toLowerCase())) {
                System.out.println(teacher);
                isExist = true;
            }
        }

        if (!isExist) {
            System.out.println("Không tìm thấy!");
        }
    }

    public static Teacher infoTeacher() {
        System.out.print("Nhập id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập name: ");
        String name = scanner.nextLine();

        System.out.print("Nhập ngày sinh: ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Nhập giới tính:\n" +
                "1. Nam.\n" +
                "2. Nữ.\n" +
                "Khác. LGBT.");
        int gender = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập chuyên môn: ");
        String technique = scanner.nextLine();

        return new Teacher(id, name, dateOfBirth, gender, technique);
    }
}