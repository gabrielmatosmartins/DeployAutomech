import { ChakraProvider } from "@chakra-ui/react";
import { SidebarProvider } from "../contexts/SidebarContext";
import '../css/styles.css'


export function MyApp({ Component, pageProps }) {
  return (
    <ChakraProvider>
      <SidebarProvider>
        <Component {...pageProps} />
      </SidebarProvider>
    </ChakraProvider>
  );
}