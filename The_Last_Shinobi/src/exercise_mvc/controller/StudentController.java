package exercise_mvc.controller;

import exercise_mvc.service.IStudentService;
import exercise_mvc.service.impl.StudentService;

import java.util.Scanner;

public class StudentController {
    public void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        IStudentService iStudentService = new StudentService();
        do {
            System.out.println("Quản lí học sinh: \n" +
                    "1. Thêm mới học sinh. \n" +
                    "2. Xóa học sinh. \n" +
                    "3. Xem danh sách học sinh. \n" +
                    "4. Tìm kiếm học sinh theo id. \n" +
                    "5. Tìm kiếm học sinh theo tên. \n" +
                    "6. Sắp xếp học sinh theo tên. \n" +
                    "7. Quay về menu chính.");
            int choose = 0;
            try {
                System.out.print("Mời bạn nhập lựa chọn: ");
                choose = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
            switch (choose) {
                case 1:
                    iStudentService.add();
                    break;
                case 2:
                    iStudentService.remove();
                    break;
                case 3:
                    iStudentService.displayAll();
                    break;
                case 4:
                    iStudentService.findId();
                    break;
                case 5:
                    iStudentService.findName();
                    break;
                case 6:
                    iStudentService.sortByName();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn của bạn không có, vui lòng chọn lại!");
            }
        } while (true);
    }
}
