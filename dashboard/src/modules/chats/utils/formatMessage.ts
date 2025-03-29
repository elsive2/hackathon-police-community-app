export const formatMessage = (message: string, maxLength: number = 40): string => {
    if (!message) return "";
    if (message.length <= maxLength) return message;
    const trimmedMessage = message.slice(0, maxLength);
    const lastSpaceIndex = trimmedMessage.lastIndexOf(" ");
    const result = lastSpaceIndex !== -1 ? trimmedMessage.slice(0, lastSpaceIndex) : trimmedMessage;
    const cleanedResult = result.trim().replace(/[,!?—.]+$/, "");

    return `${cleanedResult}...`;
};