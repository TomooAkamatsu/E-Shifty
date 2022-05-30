import { Box, useDisclosure, Wrap, WrapItem } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { dummyEmployeeList } from "../../Dummy/dummyEmployeeList";
import { EmployeeCard } from "../organisms/employee/EmployeeCard";
import { EmployeeDetailModal } from "../organisms/employee/EmployeeDetailModal";
import { useSelectEmployee } from "../../hooks/useSelectEmployee";

export const Employee: VFC = memo(() => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { onSelectEmployee, selectedEmployee } = useSelectEmployee();

  const onClickEmployee = useCallback(
    (employeeId: number) => {
      onSelectEmployee({ employeeId, onOpen });
    },
    [onOpen, onSelectEmployee]
  );

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
              onClick={onClickEmployee}
            />
          </WrapItem>
        ))}
      </Wrap>
      <EmployeeDetailModal
        isOpen={isOpen}
        onClose={onClose}
        selectedEmployee={selectedEmployee}
      />
    </Box>
  );
});
