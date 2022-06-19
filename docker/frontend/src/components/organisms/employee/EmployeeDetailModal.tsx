import {
  FormControl,
  FormLabel,
  HStack,
  Input,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  Select,
  Stack,
} from "@chakra-ui/react";
import { memo, useEffect, VFC } from "react";
import { typeEmployee } from "../../../type/typeEmployee";
import { PrimaryButton } from "../../atoms/button/PrimaryButton";
import { DeleteButton } from "../../atoms/button/DeleteButton";
import { useEmployeeRegistry } from "../../../hooks/useEmployeeRegistry";
import { useWorkingFormList } from "../../../hooks/useWorkingFormList";
import { instance } from "../../../api/axios";

type Props = {
  isOpen: boolean;
  onClose: () => void;
  selectedEmployee: typeEmployee | null;
};

export const EmployeeDetailModal: VFC<Props> = memo((props) => {
  const { isOpen, onClose, selectedEmployee } = props;
  const {
    newLastName,
    newFirstName,
    newRomanLastName,
    newRomanFirstName,
    newBirthday,
    newGender,
    newAge,
    newPhoneNumber,
    newEmail,
    newWorkingForm,
    newEmploymentDate,
    onChangeLastName,
    onChangeFirstName,
    onChangeRomanLastName,
    onChangeRomanFirstName,
    onChangeBirthday,
    onChangeAge,
    onChangeGender,
    onChangePhoneNumber,
    onChangeEmail,
    onChangeEmploymentDate,
    onChangeWorkingForm,
  } = useEmployeeRegistry();
  const { workingFormNameList, getWorkingFormData } = useWorkingFormList();

  useEffect(() => {
    getWorkingFormData();
  }, [getWorkingFormData]);

  const onClickUpdate = () => {
    let changedEmployeeData: { [key: string]: string | number } = {};
    if (newLastName !== "") changedEmployeeData.lastName = newLastName;
    if (newFirstName !== "") changedEmployeeData.firstName = newFirstName;
    if (newRomanLastName !== "")
      changedEmployeeData.romanLastName = newRomanLastName;
    if (newRomanFirstName !== "")
      changedEmployeeData.romanFirstName = newRomanFirstName;
    if (newBirthday !== "") changedEmployeeData.birthday = newBirthday;
    if (newAge !== 0) changedEmployeeData.age = newAge;
    if (newGender !== "") changedEmployeeData.gender = newGender;
    if (newPhoneNumber !== "") changedEmployeeData.phoneNumber = newPhoneNumber;
    if (newEmail !== "") changedEmployeeData.email = newEmail;
    if (newEmploymentDate !== "")
      changedEmployeeData.employmentDate = newEmploymentDate;
    if (newWorkingForm !== "")
      changedEmployeeData.workingFormName = newWorkingForm;
    instance
      .patch(
        `/employees/${selectedEmployee?.employeeId}`,
        JSON.stringify(changedEmployeeData)
      )
      .then((r) => console.log(r.data));
    console.log(changedEmployeeData);
  };

  const onClickDelete = () => {
    instance
      .delete(`/employees/${selectedEmployee?.employeeId}`)
      .then((r) => console.log(r.data));
  };

  return (
    <Modal
      isOpen={isOpen}
      onClose={onClose}
      autoFocus={false}
      motionPreset="slideInBottom"
    >
      <ModalOverlay>
        <ModalContent pb={1}>
          <ModalHeader pb={1}>従業員詳細</ModalHeader>
          <ModalCloseButton />
          <ModalBody mx={4}>
            <Stack spacing={4}>
              <h4>従業員No:&nbsp;{selectedEmployee?.employeeId}</h4>
              <HStack>
                <FormControl>
                  <FormLabel>姓</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.lastName}
                    onChange={onChangeLastName}
                  />
                </FormControl>
                <FormControl>
                  <FormLabel>名</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.firstName}
                    onChange={onChangeFirstName}
                  />
                </FormControl>
              </HStack>
              <HStack>
                <FormControl>
                  <FormLabel>姓(ローマ字)</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.romanLastName}
                    onChange={onChangeRomanLastName}
                  />
                </FormControl>
                <FormControl>
                  <FormLabel>名(ローマ字)</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.romanFirstName}
                    onChange={onChangeRomanFirstName}
                  />
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel>生年月日</FormLabel>
                <Input
                  defaultValue={selectedEmployee?.birthday}
                  onChange={onChangeBirthday}
                  type="date"
                />
              </FormControl>
              <HStack>
                <FormControl>
                  <FormLabel>性別</FormLabel>
                  <Select
                    defaultValue={selectedEmployee?.gender}
                    onChange={onChangeGender}
                  >
                    <option>男</option>
                    <option>女</option>
                  </Select>
                </FormControl>
                <FormControl>
                  <FormLabel>年齢</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.age}
                    onChange={onChangeAge}
                    type="number"
                  />
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel>TEL</FormLabel>
                <Input
                  defaultValue={selectedEmployee?.phoneNumber}
                  onChange={onChangePhoneNumber}
                />
              </FormControl>
              <FormControl>
                <FormLabel>Email</FormLabel>
                <Input
                  defaultValue={selectedEmployee?.email}
                  onChange={onChangeEmail}
                />
              </FormControl>
              <HStack>
                <FormControl>
                  <FormLabel>雇用形態</FormLabel>
                  <Select
                    defaultValue={selectedEmployee?.workingFormName}
                    onChange={onChangeWorkingForm}
                  >
                    {workingFormNameList.map((workingForm) => (
                      <option key={workingForm}>{workingForm}</option>
                    ))}
                  </Select>
                </FormControl>
                <FormControl>
                  <FormLabel>雇用開始日</FormLabel>
                  <Input
                    defaultValue={selectedEmployee?.employmentDate}
                    onChange={onChangeEmploymentDate}
                    type="date"
                  />
                </FormControl>
              </HStack>
            </Stack>
          </ModalBody>
          <ModalFooter py={1}>
            <PrimaryButton onClick={onClickUpdate}>更新</PrimaryButton>
            <DeleteButton onClick={onClickDelete}>削除</DeleteButton>
          </ModalFooter>
        </ModalContent>
      </ModalOverlay>
    </Modal>
  );
});
