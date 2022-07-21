import {
  Box,
  Center,
  Flex,
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
import { DeleteButton } from "../atoms/button/DeleteButton";
import { instance } from "../../api/axios";
import { useAuthUser } from "../../provider/login/AuthUserContext";

export const Employee: VFC = memo(() => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { onSelectEmployee, selectedEmployee } = useSelectEmployee();
  const { employeesData, getEmployees, loading } = useAllEmployees();
  const history = useHistory();
  const authUser = useAuthUser();

  const onClickEmployee = useCallback(
    (employeeId: number) => {
      onSelectEmployee({ employeeId, employeesData, onOpen });
    },
    [onOpen, onSelectEmployee, employeesData]
  );

  const onClickEmployeeRegistry = useCallback(() => {
    history.push("/employees/new");
  }, [history]);

  useEffect(() => {
    getEmployees();
  }, [getEmployees]);

  const onClickReset = useCallback(() => {
    instance.get("employees/reset").then((r) => console.log(r.data));
    history.push("/employees/redirect");
  }, [history]);

  return (
    <Box>
      <Box align="right" pr={30} pt={5} h="60px">
        <Flex float="left" pl={8}>
          <DeleteButton onClick={onClickReset}>
            リセット(初期データが再投入されます)
          </DeleteButton>
        </Flex>
        <Flex w={200}>
          {authUser?.isAdmin && (
            <PrimaryButton onClick={onClickEmployeeRegistry}>
              従業員の新規登録
            </PrimaryButton>
          )}
        </Flex>
      </Box>
      {loading ? (
        <Center h="60vh">
          <Spinner size="xl" />
        </Center>
      ) : (
        <Box>
          <Wrap spacing="20px" p={{ base: 4, md: 10 }}>
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
