import * as API from './APIService';

export const fetchWard = async (wardId: string): Promise<any> => {
    const data = await API.APIGET(`/api/wards/${wardId}`);

    return data;
};

export const updateBedStateOnServer = async (bedId: string, newBedState: string) => {
    return await API.APIPUT(`/api/beds/${bedId}/bedState`, {
        name: newBedState
    });
};