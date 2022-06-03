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
import { typeEmployee } from "../../../type/typeEmployee";
import { PrimaryButton } from "../../atoms/button/PrimaryButton";
import { DeleteButton } from "../../atoms/button/DeleteButton";

type Props = {
  isOpen: boolean;
  onClose: () => void;
  selectedEmployee: typeEmployee | null;
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
                    defaultValue={`${selectedEmployee?.lastName} ${selectedEmployee?.firstName}`}
                  />
                </FormControl>
                <FormControl>
                  <FormLabel>ローマ字</FormLabel>
                  <Input
                    defaultValue={`${selectedEmployee?.romanLastName} ${selectedEmployee?.romanFirstName}`}
                  />
                </FormControl>
              </HStack>
              <HStack>
                <FormControl>
                  <FormLabel>生年月日</FormLabel>
                  <Input defaultValue={selectedEmployee?.birthday} />
                </FormControl>
                <FormControl>
                  <FormLabel>性別</FormLabel>
                  <Input defaultValue={selectedEmployee?.gender} />
                </FormControl>
                <FormControl>
                  <FormLabel>年齢</FormLabel>
                  <Input defaultValue={selectedEmployee?.age} />
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel>TEL</FormLabel>
                <Input defaultValue={selectedEmployee?.phoneNumber} />
              </FormControl>
              <FormControl>
                <FormLabel>Email</FormLabel>
                <Input defaultValue={selectedEmployee?.email} />
              </FormControl>
              <HStack>
                <FormControl>
                  <FormLabel>雇用形態</FormLabel>
                  <Input defaultValue={selectedEmployee?.workingFormId} />
                </FormControl>
                <FormControl>
                  <FormLabel>雇用開始日</FormLabel>
                  <Input defaultValue={selectedEmployee?.employmentDate} />
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
