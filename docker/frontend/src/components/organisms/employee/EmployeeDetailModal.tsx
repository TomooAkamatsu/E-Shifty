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
  Stack,
} from "@chakra-ui/react";
import { memo, VFC } from "react";
import { Employee } from "../../../type/employee";
import { PrimaryButton } from "../../atoms/button/PrimaryButton";
import { DeleteButton } from "../../atoms/button/DeleteButton";

type Props = {
  isOpen: boolean;
  onClose: () => void;
  selectedEmployee: Employee | null;
};

export const EmployeeDetailModal: VFC<Props> = memo((props) => {
  const { isOpen, onClose, selectedEmployee } = props;

  const onClickUpdate = () => alert("更新は後ほど実装します");
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
                  <FormLabel>名前</FormLabel>
                  <Input
                    value={`${selectedEmployee?.lastName} ${selectedEmployee?.firstName}`}
                    isReadOnly
                  />
                </FormControl>
                <FormControl>
                  <FormLabel>ローマ字</FormLabel>
                  <Input
                    value={`${selectedEmployee?.romanLastName} ${selectedEmployee?.romanFirstName}`}
                    isReadOnly
                  />
                </FormControl>
              </HStack>
              <HStack>
                <FormControl>
                  <FormLabel>生年月日</FormLabel>
                  <Input value={selectedEmployee?.birthday} isReadOnly />
                </FormControl>
                <FormControl>
                  <FormLabel>性別</FormLabel>
                  <Input value={selectedEmployee?.gender} isReadOnly />
                </FormControl>
                <FormControl>
                  <FormLabel>年齢</FormLabel>
                  <Input value={selectedEmployee?.age} isReadOnly />
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel>TEL</FormLabel>
                <Input value={selectedEmployee?.phoneNumber} isReadOnly />
              </FormControl>
              <FormControl>
                <FormLabel>Email</FormLabel>
                <Input value={selectedEmployee?.email} isReadOnly />
              </FormControl>
              <HStack>
                <FormControl>
                  <FormLabel>雇用形態</FormLabel>
                  <Input value={selectedEmployee?.workingForm} isReadOnly />
                </FormControl>
                <FormControl>
                  <FormLabel>雇用開始日</FormLabel>
                  <Input value={selectedEmployee?.employmentDate} isReadOnly />
                </FormControl>
              </HStack>
            </Stack>
          </ModalBody>
          <ModalFooter py={1}>
            <PrimaryButton onClick={onClickUpdate}>更新</PrimaryButton>
            <DeleteButton onClick={onClickUpdate}>削除</DeleteButton>
          </ModalFooter>
        </ModalContent>
      </ModalOverlay>
    </Modal>
  );
});
