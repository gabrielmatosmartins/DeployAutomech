import { useState } from "react";
import { FaStar } from "react-icons/fa";
import {
    Text,
    Button,
    WrapItem,
} from "@chakra-ui/react";

const colors = {
    orange: "#FFBA5A",
    grey: "#a9a9a9"
};

const styles = {
    container: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
    },
    stars: {
        display: "flex",
        flexDirection: "row",
    },
    textarea: {
        border: "1px solid #a9a9a9",
        borderRadius: 5,
        padding: 10,
        margin: "20px 0",
        minHeight: 100,
        width: 300
    },
    button: {
        border: "1px solid #a9a9a9",
        borderRadius: 5,
        width: 300,
        padding: 10,
    }
};


function Avaliacao() {
    const [textoAvaliacao, setTextoAvaliacao] = useState("");
    const [notaAvaliacao, setNotaAvaliacao] = useState(0);
    const [currentValue, setCurrentValue] = useState(0);
    const [hoverValue, setHoverValue] = useState(undefined);
    const stars = Array(5).fill(0);

    const handleClick = value => {
        setCurrentValue(value);
        setNotaAvaliacao(value);
    }

    const handleMouseOver = newHoverValue => {
        setHoverValue(newHoverValue);
    };

    const handleMouseLeave = () => {
        setHoverValue(undefined);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        const url = 'http://4.227.162.137:8080/Avaliacao/todos';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                textoavaliacao: textoAvaliacao,
                notaavaliacao: notaAvaliacao,
                idcliente: 1,
                oficinas_idoficinas: 1,
            }),
        });

        const data = await response.json();
        console.log(data);
        window.location.reload();
    };

    return (
        <div style={styles.container}>
            <Text noOfLines={1} as='b' color={"black"} padding={10}> Avaliação da sua Experiência</Text>
            <Text noOfLines={1} as='a' color={"black"} padding={1}>Como você avalia a experiência na AutoMech </Text>
            <div style={styles.stars}>
                {stars.map((_, index) => {
                    return (
                        <FaStar
                            key={index}
                            size={24}
                            onClick={() => handleClick(index + 1)}
                            onMouseOver={() => handleMouseOver(index + 1)}
                            onMouseLeave={handleMouseLeave}
                            color={(hoverValue || currentValue) > index ? colors.orange : colors.grey}
                            style={{
                                marginRight: 10,
                                cursor: "pointer"
                            }}
                        />
                    )
                })}
            </div>
            <textarea
                placeholder="Escreva aqui seu comentário"
                style={styles.textarea}
                value={textoAvaliacao}
                onChange={(e) => setTextoAvaliacao(e.target.value)}
            />
            <WrapItem>
                <Button colorScheme='yellow' size='md'
                    height='48px'
                    width='300px'
                    onClick={handleSubmit}
                >
                    Avaliar
                </Button>
            </WrapItem>
        </div>
    );
};

export default Avaliacao;
