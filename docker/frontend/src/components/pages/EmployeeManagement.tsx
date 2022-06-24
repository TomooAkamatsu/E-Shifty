import {
  Box,
  Center,
  Spinner,
  useDisclosure,
  Wrap,
  WrapItem,
} from "@chakra-ui/react";
import { memo, useCallback, useEffect, VFC } from "react";
import { EmployeeCard } from "../organisms/employee/EmployeeCard";
import { EmployeeDetailModal } from "../organisms/employee/EmployeeDetailModal";
import { useSelectEmployee } from "../../hooks/useSelectEmployee";
import { PrimaryButton } from "../atoms/button/PrimaryButton";
import { useHistory } from "react-router-dom";
import { useAllEmployees } from "../../hooks/useAllEmployees";

export const Employee: VFC = memo(() => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { onSelectEmployee, selectedEmployee } = useSelectEmployee();
  const { employeesData, getEmployees, loading } = useAllEmployees();
  const history = useHistory();

  const onClickEmployee = useCallback(
    (employeeId: number) => {
      onSelectEmployee({ employeeId, employeesData, onOpen });
    },
    [onOpen, onSelectEmployee, employeesData]
  );

  const onClickEmployeeRegistry = useCallback(() => {
    history.push("/shiftwork_management/employees/new");
  }, [history]);

  useEffect(() => {
    getEmployees();
  }, [getEmployees]);

  return (
    <Box>
      <Box align="right" pr={30} pt={5}>
        <PrimaryButton onClick={onClickEmployeeRegistry}>
          従業員の新規登録
        </PrimaryButton>
      </Box>
      {loading ? (
        <Center h="60vh">
          <Spinner size="xl" />
        </Center>
      ) : (
        <Box>
          <Wrap spacing="30px" p={{ base: 4, md: 10 }}>
            {employeesData.map((employee) => (
              <WrapItem key={employee.employeeId}>
                <EmployeeCard
                  employeeId={employee.employeeId}
                  lastName={employee.lastName}
                  firstName={employee.firstName}
                  romanLastName={employee.romanLastName}
                  romanFirstName={employee.romanFirstName}
                  phoneNumber={employee.phoneNumber}
                  email={employee.email}
                  workingFormName={employee.workingFormName}
                  onClick={onClickEmployee}
                />
              </WrapItem>
            ))}
          </Wrap>
        </Box>
      )}
      <EmployeeDetailModal
        isOpen={isOpen}
        onClose={onClose}
        selectedEmployee={selectedEmployee}
      />
    </Box>
  );
});
