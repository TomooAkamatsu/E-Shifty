import { Box, Wrap, WrapItem } from "@chakra-ui/react";
import { memo, VFC } from "react";
import { dummyEmployeeList } from "../../Dummy/dummyEmployeeList";
import { EmployeeCard } from "../organisms/employee/EmployeeCard";

export const Employee: VFC = memo(() => {
  return (
    <Box>
      <Wrap spacing="30px" p={{ base: 4, md: 10 }}>
        {dummyEmployeeList.map((employee) => (
          <WrapItem key={employee.employeeId}>
            <EmployeeCard
              employeeId={employee.employeeId}
              lastName={employee.lastName}
              firstName={employee.firstName}
              romanLastName={employee.romanLastName}
              romanFirstName={employee.romanFirstName}
              phoneNumber={employee.phoneNumber}
              email={employee.email}
              workingForm={employee.workingForm}
            />
          </WrapItem>
        ))}
      </Wrap>
    </Box>
  );
});
