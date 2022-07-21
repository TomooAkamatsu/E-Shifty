import {
  Box,
  Center,
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
  Text,
  useDisclosure,
} from "@chakra-ui/react";
import { memo, useEffect, VFC } from "react";
import { typeEmployee } from "../../../type/typeEmployee";
import { PrimaryButton } from "../../atoms/button/PrimaryButton";
import { DeleteButton } from "../../atoms/button/DeleteButton";
import { useWorkingFormList } from "../../../hooks/useWorkingFormList";
import { instance } from "../../../api/axios";
import { useMessage } from "../../../hooks/useMessage";
import { useHistory } from "react-router-dom";
import { useEmployeeUpdate } from "../../../hooks/useEmployeeUpdate";
import { useAuthUser } from "../../../provider/login/AuthUserContext";

type Props = {
  isOpen: boolean;
  onClose: () => void;
  selectedEmployee: typeEmployee | null;
};

export const EmployeeDetailModal: VFC<Props> = memo((props) => {
  const { isOpen, onClose, selectedEmployee } = props;
  const { showMessage } = useMessage();
  const history = useHistory();
  const authUser = useAuthUser();
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
  } = useEmployeeUpdate();
  const { workingFormNameList, getWorkingFormData } = useWorkingFormList();
  const confirmRetirement = useDisclosure();

  useEffect(() => {
    getWorkingFormData();
  }, [getWorkingFormData]);

  const onClickUpdate = () => {
    let changedEmployeeData: { [key: string]: string | number | null } = {};

    if (newLastName !== null) changedEmployeeData.lastName = newLastName;
    if (newFirstName !== null) changedEmployeeData.firstName = newFirstName;
    if (newRomanLastName !== null)
      changedEmployeeData.romanLastName = newRomanLastName;
    if (newRomanFirstName !== null)
      changedEmployeeData.romanFirstName = newRomanFirstName;
    if (newBirthday !== null) changedEmployeeData.birthday = newBirthday;
    if (newAge !== null) changedEmployeeData.age = newAge;
    if (newGender !== null) changedEmployeeData.gender = newGender;
    if (newPhoneNumber !== null)
      changedEmployeeData.phoneNumber = newPhoneNumber;
    if (newEmail !== null) changedEmployeeData.email = newEmail;
    if (newEmploymentDate !== null)
      changedEmployeeData.employmentDate = newEmploymentDate;
    if (newWorkingForm !== null)
      changedEmployeeData.workingFormName = newWorkingForm;

    instance
      .patch(
        `/employees/${selectedEmployee?.employeeId}`,
        JSON.stringify(changedEmployeeData)
      )
      .then((r) => {
        console.log(r.data);
        showMessage({
          title: "更新が完了しました",
          status: "success",
        });
        history.push("/employees/redirect");
      })
      .catch(() => {
        showMessage({
          title: "更新内容が正しくないか空です",
          status: "error",
        });
      });
    console.log(changedEmployeeData);
  };

  const onClickRetirement = () => {
    confirmRetirement.onOpen();
    instance
      .delete(`/employees/${selectedEmployee?.employeeId}`)
      .then((r) => {
        console.log(r.data);
        showMessage({
          title: "削除が完了しました",
          status: "success",
        });
        history.push("/employees/redirect");
      })
      .catch(() => {
        showMessage({
          title: "削除に失敗しました",
          status: "error",
        });
      });
  };

  const today = new Date();
  const todayString = `${today.getFullYear()}年${today.getMonth()}月${today.getDate()}日`;

  return (
    <>
      <Modal
        isOpen={isOpen}
        onClose={onClose}
        autoFocus={false}
        motionPreset="slideInBottom"
        isCentered
      >
        <ModalOverlay>
          <ModalContent pb={3}>
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
            {authUser?.isAdmin && (
              <ModalFooter py={1}>
                <PrimaryButton onClick={onClickUpdate}>更新</PrimaryButton>
                <DeleteButton onClick={confirmRetirement.onOpen}>
                  退職
                </DeleteButton>
              </ModalFooter>
            )}
          </ModalContent>
        </ModalOverlay>
      </Modal>
      <Modal
        isOpen={confirmRetirement.isOpen}
        onClose={confirmRetirement.onClose}
        autoFocus={false}
        motionPreset="slideInBottom"
        isCentered
      >
        <ModalOverlay>
          <ModalContent p={3} justifyContent="center">
            <ModalHeader align="center" pb={0}>
              {`退職日は${todayString}になります`}
              <br />
              本当によろしいですか？
            </ModalHeader>
            <ModalBody align="center">
              <HStack>
                <Box w="50%">
                  <PrimaryButton onClick={confirmRetirement.onClose}>
                    &emsp;戻る &emsp;
                  </PrimaryButton>
                </Box>
                <Box w="50%">
                  <DeleteButton onClick={onClickRetirement}>
                    &emsp;退職 &emsp;
                  </DeleteButton>
                </Box>
              </HStack>
            </ModalBody>
          </ModalContent>
        </ModalOverlay>
      </Modal>
    </>
  );
});
