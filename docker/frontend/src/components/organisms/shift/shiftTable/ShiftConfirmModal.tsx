import {
  Box,
  HStack,
  Modal,
  ModalBody,
  ModalContent,
  ModalHeader,
  ModalOverlay,
} from "@chakra-ui/react";
import { VFC } from "react";
import { PrimaryButton } from "../../../atoms/button/PrimaryButton";

type Props = {
  isOpen: boolean;
  onClose: () => void;
  onClickConfirm: () => void;
};

export const ShiftConfirmModal: VFC<Props> = (props) => {
  const { isOpen, onClose, onClickConfirm } = props;
  return (
    <Modal
      isOpen={isOpen}
      onClose={onClose}
      autoFocus={false}
      motionPreset="slideInBottom"
    >
      <ModalOverlay>
        <ModalContent p={3}>
          <ModalHeader align="center" pb={0}>
            本当にこれで確定しますか？
          </ModalHeader>
          <ModalBody align="center">
            <HStack>
              <Box w="50%">
                <PrimaryButton onClick={onClose}>
                  &emsp;戻る &emsp;
                </PrimaryButton>
              </Box>
              <Box w="50%">
                <PrimaryButton onClick={onClickConfirm}>
                  &emsp;確定 &emsp;
                </PrimaryButton>
              </Box>
            </HStack>
          </ModalBody>
        </ModalContent>
      </ModalOverlay>
    </Modal>
  );
};
