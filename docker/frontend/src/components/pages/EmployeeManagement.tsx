import { Box, useDisclosure, Wrap, WrapItem } from "@chakra-ui/react";
import { memo, useCallback, VFC } from "react";
import { dummyEmployeeList } from "../../dummy/dummyEmployeeList";
import { EmployeeCard } from "../organisms/employee/EmployeeCard";
import { EmployeeDetailModal } from "../organisms/employee/EmployeeDetailModal";
import { useSelectEmployee } from "../../hooks/useSelectEmployee";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { useHistory } from "react-router-dom";

export const Employee: VFC = memo(() => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { onSelectEmployee, selectedEmployee } = useSelectEmployee();
  const history = useHistory();

  const onClickEmployee = useCallback(
    (employeeId: number) => {
      onSelectEmployee({ employeeId, onOpen });
    },
    [onOpen, onSelectEmployee]
  );

  const onClickEmployeeRegistry = useCallback(() => {
    history.push("/shiftwork_management/employees/new");
  }, [history]);

  return (
    <Box>
      <Box align="right" pr={30} pt={5}>
        <PrimaryButton onClick={onClickEmployeeRegistry}>
          従業員の新規登録
        </PrimaryButton>
      </Box>
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
      </Box>

      <EmployeeDetailModal
        isOpen={isOpen}
        onClose={onClose}
        selectedEmployee={selectedEmployee}
      />
    </Box>
  );
});
