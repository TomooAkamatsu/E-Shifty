import { ChakraProvider } from "@chakra-ui/react";
import { BrowserRouter } from "react-router-dom";
import AuthUserProvider from "./provider/login/AuthUserContext";

import { Router } from "./router/Router";
import theme from "./theme/theme";

function App() {
  return (
    <ChakraProvider theme={theme}>
      <AuthUserProvider>
        <BrowserRouter>
          <Router />
        </BrowserRouter>
      </AuthUserProvider>
    </ChakraProvider>
  );
}

export default App;
