package case_study.service.impl;

import case_study.exception.NumberRegexInvalidException;
import case_study.model.Booking;
import case_study.model.Customer;
import case_study.model.Facility;
import case_study.model.Link;
import case_study.service.IPromotionService;
import case_study.utils.ReadWriteBookingFileUtil;
import case_study.utils.ReadWriteCustomerFileUtil;
import case_study.utils.ReadWriteFacilityFileUtil;

import java.util.*;

public class PromotionService implements IPromotionService {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void displayUseService() {
        TreeSet<Booking> bookingList = ReadWriteBookingFileUtil.readBookingFile(Link.PATH_BOOKING.getPath());
        List<Customer> customerList = ReadWriteCustomerFileUtil.readCustomerFile(Link.PATH_CUSTOMER.getPath());
        Map<Facility, Integer> facilityList = ReadWriteFacilityFileUtil.readFacilityFile(Link.PATH_VILLA.getPath());
        facilityList.putAll(ReadWriteFacilityFileUtil.readFacilityFile(Link.PATH_HOUSE.getPath()));
        facilityList.putAll(ReadWriteFacilityFileUtil.readFacilityFile(Link.PATH_ROOM.getPath()));
        Set<Facility> facilitySet = facilityList.keySet();

        System.out.print("\nNhập năm sử dụng dịch vụ: ");
        String year;
        while (true) {
            try {
                year = SCANNER.nextLine();
                if (!year.matches("^2\\d{3}$")) {
                    throw new NumberRegexInvalidException("Năm phải đúng định dạng 2XXX (X là các số từ 0-9), vui lòng nhập lại!");
                }
                break;
            } catch (NumberRegexInvalidException e) {
                System.out.println(e.getMessage());
            }
        }

        List<String> serviceIdFacilityList = new ArrayList<>();
        for (Facility facility : facilitySet) {
            if (facility.getRentalType().equals("Nam")) {
                serviceIdFacilityList.add(facility.getServiceId());
            }
        }

        List<String> customerIdList = new ArrayList<>();
        for (Booking booking : bookingList) {
            String[] arr = booking.getStartDay().split("/");
            if (year.equals(arr[2])) {
                for (String serviceIdFacility : serviceIdFacilityList) {
                    if (booking.getServiceId().equals(serviceIdFacility)) {
                        customerIdList.add(booking.getCustomerId());
                    }
                }
            }
        }

        if (customerIdList.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng sử dụng dịch vụ theo năm (trong năm " + year + "): ");
        } else {
            System.out.println("Danh sách khách hàng đã sử dụng dịch vụ theo năm (trong năm " + year + "): ");
            for (Customer customer : customerList) {
                for (String customerId : customerIdList) {
                    if (customer.getId().equals(customerId)) {
                        System.out.println(customer);
                    }
                }
            }
        }
    }

    @Override
    public void displayGetVoucher() {

    }
}